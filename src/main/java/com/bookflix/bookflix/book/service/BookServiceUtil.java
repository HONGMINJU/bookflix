package com.bookflix.bookflix.book.service;

import com.bookflix.bookflix.book.dto.externalDTO.bestSellerList.BestSellerDTO;
import com.bookflix.bookflix.book.dto.externalDTO.bestSellerList.BestSellerListDTO;
import com.bookflix.bookflix.book.dto.externalDTO.bookInfo.BookInfoResponseDTO;
import com.bookflix.bookflix.book.dto.externalDTO.haveInfo.HaveInfoResponseDTO;
import com.bookflix.bookflix.book.dto.externalDTO.recommendList.ISBNListDTO;
import com.bookflix.bookflix.book.dto.response.LibraryInfo;
import com.bookflix.bookflix.book.entity.Book;
import com.bookflix.bookflix.book.repository.BookRepository;
import com.bookflix.bookflix.library.entity.NearLibrary;
import com.bookflix.bookflix.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceUtil {

    @Value("${APIKey}")
    private String APIKey;

    private final BookRepository bookRepository;

    private static final String RECOMMEND_URL = "http://3.39.119.118:5000/recommend";
    private static final String SIMILAR_URL = "http://3.39.119.118:5000/similar";
    private static final String NEW_SIMILAR_URL = "http://3.39.119.118:5000/similarNew";

    public LibraryInfo getLibraryInfo(NearLibrary nearLibrary, String isbn){
        String URL = "http://data4library.kr/api/bookExist?authKey=" + APIKey
                + "&libCode=" + Integer.toString(nearLibrary.getLibrary().getCode())
                + "&isbn13="+isbn;
        RestTemplate restTemplate = new RestTemplate();
        HaveInfoResponseDTO responseDTO = restTemplate.getForObject(URL, HaveInfoResponseDTO.class);
        boolean have = (responseDTO.getResultDTO().getHasBook().equals("Y"));
        boolean canBorrow = (responseDTO.getResultDTO().getLoanAvailable().equals("Y"));
        return LibraryInfo.of(nearLibrary, have, canBorrow);
    }

    public List<String> getBestSeller(User user){
        String URL = "http://data4library.kr/api/loanItemSrchByLib?authKey=" + APIKey
                + "&region=" + Integer.toString(user.getRegion())
                + "&age=" + Integer.toString(user.getAge())
                + "&pageSize=8";
        RestTemplate restTemplate = new RestTemplate();
        BestSellerListDTO responseDTO = restTemplate.getForObject(URL, BestSellerListDTO.class);
        List<String> collect = responseDTO.getBestSellerDTOList().stream()
                .map(BestSellerDTO::getIsbn13).collect(Collectors.toList());
        System.out.println("responseDTO.getBestSellerDTOList().size() = " + responseDTO.getBestSellerDTOList().size());
        return collect;
    }

    public List<String> getISBNListFromML(List<String> isbnList) {
        // body 설정
        MultiValueMap<String, List<String>> params = new LinkedMultiValueMap<>();
        params.add("isbn", isbnList);

        RestTemplate restTemplate = new RestTemplate();
        ISBNListDTO isbnListDTO = restTemplate.postForObject(RECOMMEND_URL, params, ISBNListDTO.class);

        return isbnListDTO.getIsbn();
    }

    public List<String> getNewISBNListFromML(List<String> isbnList) {
        // body 설정
        MultiValueMap<String, List<String>> params = new LinkedMultiValueMap<>();
        params.add("isbn", isbnList);

        RestTemplate restTemplate = new RestTemplate();
        ISBNListDTO isbnListDTO = restTemplate.postForObject(NEW_SIMILAR_URL, params, ISBNListDTO.class);
        // TODO : NEW_SIMILAR_URL
        return isbnListDTO.getIsbn();
    }

    public List<String> getSimilarISBNListFromML(List<String> isbnList) {
        // body 설정
        MultiValueMap<String, List<String>> params = new LinkedMultiValueMap<>();
        params.add("isbn", isbnList);

        RestTemplate restTemplate = new RestTemplate();
        ISBNListDTO isbnListDTO = restTemplate.postForObject(SIMILAR_URL, params, ISBNListDTO.class);

        return isbnListDTO.getIsbn();
    }

    @Transactional
    public Book postBook(String isbn){
        String URL = "http://data4library.kr/api/srchDtlList?authKey=" + APIKey + "&isbn13=" + isbn;
        RestTemplate restTemplate = new RestTemplate();
        BookInfoResponseDTO responseDTO = restTemplate.getForObject(URL, BookInfoResponseDTO.class);
        return bookRepository.save(Book.of(responseDTO));
    }
}
