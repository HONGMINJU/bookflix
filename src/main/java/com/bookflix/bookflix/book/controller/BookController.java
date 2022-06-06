package com.bookflix.bookflix.book.controller;

import com.bookflix.bookflix.book.dto.response.GetBookRes;
import com.bookflix.bookflix.book.service.BookService;
import com.bookflix.bookflix.common.response.BaseException;
import com.bookflix.bookflix.common.response.BaseResponse;
import com.bookflix.bookflix.common.service.JwtService;
import com.bookflix.bookflix.user.dto.response.GetUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bookflix.bookflix.common.response.BaseResponseStatus.INVALID_USERID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    private final JwtService jwtService;

    @GetMapping("/{isbn}")
    public BaseResponse<GetBookRes> getBook(@PathVariable("isbn") String isbn) {
        Long userId = jwtService.getUserIdx();
        GetBookRes getBookRes = bookService.getBook(userId, isbn);
        return new BaseResponse<>(getBookRes);
    }
}
