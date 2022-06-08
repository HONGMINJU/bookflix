package com.bookflix.bookflix.book.service;

import com.bookflix.bookflix.book.dto.externalDTO.bookInfo.BookInfoResponseDTO;
import com.bookflix.bookflix.book.dto.externalDTO.recommendList.ISBNListDTO;
import com.bookflix.bookflix.book.dto.response.*;
import com.bookflix.bookflix.book.dto.externalDTO.haveInfo.HaveInfoResponseDTO;
import com.bookflix.bookflix.book.entity.Book;
import com.bookflix.bookflix.book.repository.BookRepository;
import com.bookflix.bookflix.common.response.BaseException;
import com.bookflix.bookflix.common.response.BaseResponseStatus;
import com.bookflix.bookflix.library.entity.NearLibrary;
import com.bookflix.bookflix.library.repository.NearLibraryRepository;
import com.bookflix.bookflix.user.entity.User;
import com.bookflix.bookflix.user.repository.HistoryRepository;
import com.bookflix.bookflix.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    private final NearLibraryRepository nearLibraryRepository;

    private final BookServiceUtil bookServiceUtil;

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
            libraryInfoList.add(bookServiceUtil.getLibraryInfo(nearLibrary, isbn));
        }
        return GetBookRes.of(book, libraryInfoList);
    }

}
