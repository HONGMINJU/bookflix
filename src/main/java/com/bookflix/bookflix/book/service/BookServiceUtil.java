package com.bookflix.bookflix.book.service;

import com.bookflix.bookflix.book.dto.externalDTO.bestSellerList.BestSellerDTO;
import com.bookflix.bookflix.book.dto.externalDTO.bestSellerList.BestSellerListDTO;
import com.bookflix.bookflix.book.dto.externalDTO.bookInfo.BookInfoResponseDTO;
import com.bookflix.bookflix.book.dto.externalDTO.haveInfo.HaveInfoResponseDTO;
import com.bookflix.bookflix.book.dto.externalDTO.inhaLoanInfo.InhaLoanInfoDTO;
import com.bookflix.bookflix.book.dto.externalDTO.recommendList.ISBNListDTO;
import com.bookflix.bookflix.book.dto.externalDTO.searchBookDTO.SearchBookResponseDTO;
import com.bookflix.bookflix.book.dto.response.LibraryInfo;
import com.bookflix.bookflix.book.entity.Book;
import com.bookflix.bookflix.book.repository.BookRepository;
import com.bookflix.bookflix.library.entity.NearLibrary;
import com.bookflix.bookflix.user.entity.User;
import com.bookflix.bookflix.user.entity.enumType.Gender;
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
    private static final String INHA_LOAN_URL = "http://3.39.119.118:5000/getLoanData";

    public LibraryInfo getLibraryInfo(NearLibrary nearLibrary, String isbn){
        String URL = "http://data4library.kr/api/bookExist?authKey=" + APIKey
                + "&libCode=" + Integer.toString(nearLibrary.getLibrary().getCode())
                + "&isbn13="+isbn;
        RestTemplate restTemplate = new RestTemplate();
        HaveInfoResponseDTO responseDTO = restTemplate.getForObject(URL, HaveInfoResponseDTO.class);
        boolean have = (responseDTO.getResultDTO().getHasBook().equals("Y")? true : false);
        boolean canBorrow = (responseDTO.getResultDTO().getLoanAvailable().equals("Y")? true : false);
        return LibraryInfo.of(nearLibrary, have, canBorrow);
    }

    public List<String> getBestSeller(User user){
        String URL = "http://data4library.kr/api/loanItemSrchByLib?authKey=" + APIKey
                + "&region=" + Integer.toString(user.getRegion())
                + "&age=" + Integer.toString(user.getAge())
                + "&gender=" + (user.getGender().equals(Gender.MALE)? "0" : "1")
                + "&pageSize=8";
        RestTemplate restTemplate = new RestTemplate();
        BestSellerListDTO responseDTO = restTemplate.getForObject(URL, BestSellerListDTO.class);
        List<String> collect = responseDTO.getBestSellerDTOList().stream()
                .map(BestSellerDTO::getIsbn13).collect(Collectors.toList());
        System.out.println("responseDTO.getBestSellerDTOList().size() = " + responseDTO.getBestSellerDTOList().size());
        return collect;
    }

    public String getISBN(String title){
        String URL = "http://data4library.kr/api/loanItemSrchByLib?authKey=" + APIKey
                + "&keyword=\"" + title + "\"&pageNo=1&pageSize=1";
        RestTemplate restTemplate = new RestTemplate();
        SearchBookResponseDTO responseDTO = restTemplate.getForObject(URL, SearchBookResponseDTO.class);
        if (responseDTO.getSearchBookList().size() <= 0)
            return null;
        return responseDTO.getSearchBookList().get(0).getIsbn13();
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

    public List<String> getInhaLoanISBNListFromML(String username, String password) {
        // body 설정
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", username);
        params.add("password", password);

        RestTemplate restTemplate = new RestTemplate();
        InhaLoanInfoDTO loanTitleList = restTemplate.postForObject(INHA_LOAN_URL, params, InhaLoanInfoDTO.class);

        return loanTitleList.getNames();
    }

    @Transactional
    public Book postBook(String isbn){
        String URL = "http://data4library.kr/api/srchDtlList?authKey=" + APIKey + "&isbn13=" + isbn;
        RestTemplate restTemplate = new RestTemplate();
        BookInfoResponseDTO responseDTO = restTemplate.getForObject(URL, BookInfoResponseDTO.class);
        return bookRepository.save(Book.of(responseDTO));
    }
}
