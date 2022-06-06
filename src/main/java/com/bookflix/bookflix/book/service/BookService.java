package com.bookflix.bookflix.book.service;

import com.bookflix.bookflix.book.dto.response.GetBookRes;
import com.bookflix.bookflix.book.dto.response.SearchBookRes;
import com.bookflix.bookflix.book.entity.Book;

import java.util.List;

public interface BookService {
    public GetBookRes getBook(Long userId, String isbn);
    public SearchBookRes searchBooks(String keyword);
    public List<Book> getBookRecommendList(List<String> isbnList);
}
