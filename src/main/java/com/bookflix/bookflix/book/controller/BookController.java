package com.bookflix.bookflix.book.controller;

import com.bookflix.bookflix.book.dto.response.GetBookRes;
import com.bookflix.bookflix.book.dto.response.GetSimilarBookListRes;
import com.bookflix.bookflix.book.dto.response.SearchBookRes;
import com.bookflix.bookflix.book.service.BookService;
import com.bookflix.bookflix.common.response.BaseResponse;
import com.bookflix.bookflix.common.service.JwtService;
import com.bookflix.bookflix.user.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    private final RecommendService recommendService;

    private final JwtService jwtService;

    @GetMapping("")
    public BaseResponse<SearchBookRes> searchBooks(@RequestParam("keyword") String keyword){
        return new BaseResponse<>(bookService.searchBooks(keyword));
    }

    @GetMapping("/similar")
    public BaseResponse<GetSimilarBookListRes> getSimilarBookList(@RequestBody Map<String, List<String>> isbnInfo){
        return new BaseResponse<>(recommendService.getSimilarBookList(isbnInfo.get("ISBNList")));
    }

    @GetMapping("/{isbn}")
    public BaseResponse<GetBookRes> getBook(@PathVariable("isbn") String isbn) {
        Long userId = jwtService.getUserIdx();
        GetBookRes getBookRes = bookService.getBook(userId, isbn);
        return new BaseResponse<>(getBookRes);
    }
}
