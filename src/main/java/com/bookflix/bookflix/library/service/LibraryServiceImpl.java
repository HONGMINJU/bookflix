package com.bookflix.bookflix.library.service;

import com.bookflix.bookflix.library.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LibraryServiceImpl implements LibraryService{

    private final LibraryRepository libraryRepository;
}
