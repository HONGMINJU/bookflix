package com.bookflix.bookflix.book.controller;

import com.bookflix.bookflix.book.dto.response.GetBookRes;
import com.bookflix.bookflix.book.dto.response.SearchBookRes;
import com.bookflix.bookflix.book.service.BookService;
import com.bookflix.bookflix.common.response.BaseResponse;
import com.bookflix.bookflix.common.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    private final JwtService jwtService;

    @GetMapping("")
    public BaseResponse<SearchBookRes> searchBooks(@RequestParam("keyword") String keyword){
        return new BaseResponse<>(bookService.searchBooks(keyword));
    }

    @GetMapping("/{isbn}")
    public BaseResponse<GetBookRes> getBook(@PathVariable("isbn") String isbn) {
        Long userId = jwtService.getUserIdx();
        GetBookRes getBookRes = bookService.getBook(userId, isbn);
        return new BaseResponse<>(getBookRes);
    }
}
