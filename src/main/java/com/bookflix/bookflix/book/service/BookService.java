package com.bookflix.bookflix.book.service;

import com.bookflix.bookflix.book.dto.response.GetBookRes;

public interface BookService {
    public GetBookRes getBook(Long userId, String isbn);
}
