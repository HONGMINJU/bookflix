package com.bookflix.bookflix.book.service;

import com.bookflix.bookflix.book.dto.response.GetBookRes;
import com.bookflix.bookflix.book.dto.response.SearchBookRes;

public interface BookService {
    public GetBookRes getBook(Long userId, String isbn);
    public SearchBookRes searchBooks(String keyword);
}
