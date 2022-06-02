package com.bookflix.bookflix.user.service;

import com.bookflix.bookflix.book.entity.Book;
import com.bookflix.bookflix.book.repository.BookRepository;
import com.bookflix.bookflix.library.entity.Library;
import com.bookflix.bookflix.library.entity.NearLibrary;
import com.bookflix.bookflix.user.entity.History;
import com.bookflix.bookflix.user.entity.User;
import com.bookflix.bookflix.user.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    private final BookRepository bookRepository;

    @Override
    public void createAndAddHistory(User user, String isbn){
        Book book = bookRepository.findById(isbn).orElseThrow();
        History history = historyRepository.save(new History(user, book));
        user.addHistory(history);
    }
}
