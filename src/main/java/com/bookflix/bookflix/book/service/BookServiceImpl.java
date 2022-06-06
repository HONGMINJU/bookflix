package com.bookflix.bookflix.book.service;

import com.bookflix.bookflix.book.dto.response.GetBookRes;
import com.bookflix.bookflix.book.dto.response.LibraryInfo;
import com.bookflix.bookflix.book.entity.Book;
import com.bookflix.bookflix.book.repository.BookRepository;
import com.bookflix.bookflix.common.response.BaseException;
import com.bookflix.bookflix.common.response.BaseResponseStatus;
import com.bookflix.bookflix.library.entity.NearLibrary;
import com.bookflix.bookflix.library.repository.NearLibraryRepository;
import com.bookflix.bookflix.user.entity.User;
import com.bookflix.bookflix.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final NearLibraryRepository nearLibraryRepository;

    public LibraryInfo getLibraryInfo(NearLibrary nearLibrary, String isbn){

        //http://data4library.kr/api/bookExist?authKey=[발급받은키]&libCode=[도서관코드]&isbn13=9788934939603
        return LibraryInfo.of(nearLibrary, false, false);
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
}
