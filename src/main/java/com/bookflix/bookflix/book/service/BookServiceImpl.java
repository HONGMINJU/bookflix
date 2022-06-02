package com.bookflix.bookflix.book.service;

import com.bookflix.bookflix.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
}
