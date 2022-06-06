package com.bookflix.bookflix.book.service;

import com.bookflix.bookflix.book.dto.externalDTO.recommendList.ISBNListDTO;
import com.bookflix.bookflix.book.dto.response.GetBookInfoRes;
import com.bookflix.bookflix.book.dto.response.GetBookRes;
import com.bookflix.bookflix.book.dto.response.LibraryInfo;
import com.bookflix.bookflix.book.dto.response.SearchBookRes;
import com.bookflix.bookflix.book.dto.externalDTO.haveInfo.responseDTO;
import com.bookflix.bookflix.book.entity.Book;
import com.bookflix.bookflix.book.repository.BookRepository;
import com.bookflix.bookflix.common.response.BaseException;
import com.bookflix.bookflix.common.response.BaseResponseStatus;
import com.bookflix.bookflix.library.entity.NearLibrary;
import com.bookflix.bookflix.library.repository.NearLibraryRepository;
import com.bookflix.bookflix.user.entity.User;
import com.bookflix.bookflix.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService{

    @Value("${APIKey}")
    private String APIKey;

    private static final String RECOMMEND_URL = "http://3.39.119.118:5000/recommend";

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final NearLibraryRepository nearLibraryRepository;

    public LibraryInfo getLibraryInfo(NearLibrary nearLibrary, String isbn){
        String URL = "http://data4library.kr/api/bookExist?authKey=" + APIKey
                + "&libCode=" + Integer.toString(nearLibrary.getLibrary().getCode())
                + "&isbn13="+isbn;
        RestTemplate restTemplate = new RestTemplate();
        responseDTO responseDTO = restTemplate.getForObject(URL, responseDTO.class);
        boolean have = (responseDTO.getResultDTO().getHasBook().equals("Y")? true : false);
        boolean canBorrow = (responseDTO.getResultDTO().getLoanAvailable().equals("Y")? true : false);
        return LibraryInfo.of(nearLibrary, have, canBorrow);
    }

    public List<String> getISBNListFromML(List<String> isbnList) {
        // body 설정
        MultiValueMap<String, List<String>> params = new LinkedMultiValueMap<>();
        params.add("isbn", isbnList);

        RestTemplate restTemplate = new RestTemplate();
        ISBNListDTO isbnListDTO = restTemplate.postForObject(RECOMMEND_URL, params, ISBNListDTO.class);

        return isbnListDTO.getIsbn();
    }

    @Override
    public SearchBookRes searchBooks(String keyword){
        List<Book> bookList = bookRepository.findByTitleContaining(keyword);
        List<GetBookInfoRes> bookInfoList = bookList.stream().map(GetBookInfoRes::of).collect(Collectors.toList());
        return SearchBookRes.builder().bookList(bookInfoList).build();
    }

    @Override
    public GetBookRes getBook(Long userId, String isbn){
        Book book = bookRepository.findById(isbn)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.BOOK_NOT_EXIST));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_EXIST));
        List<NearLibrary> nearLibraryList = nearLibraryRepository.findAllByUser(user);
        List<LibraryInfo> libraryInfoList = new ArrayList<>();
        for (NearLibrary nearLibrary : nearLibraryList){
            libraryInfoList.add(getLibraryInfo(nearLibrary, isbn));
        }
        return GetBookRes.of(book, libraryInfoList);
    }

    @Override
    public List<Book> getBookRecommendList(List<String> isbnList){
        List<Book> bookList = new ArrayList<>();
        List<String> recommendISBNList = getISBNListFromML(isbnList);
        // TODO : recommendISBNList 잘 되는 거 확인했음 => book list로 만들 차례임
        return bookList;
    }
}
