package com.bookflix.bookflix.user.service;

import com.bookflix.bookflix.book.entity.Book;
import com.bookflix.bookflix.book.repository.BookRepository;
import com.bookflix.bookflix.common.response.BaseException;
import com.bookflix.bookflix.common.response.BaseResponseStatus;
import com.bookflix.bookflix.user.dto.response.BorrowHistoryInfo;
import com.bookflix.bookflix.user.dto.response.GetBorrowHistoryRes;
import com.bookflix.bookflix.user.entity.History;
import com.bookflix.bookflix.user.entity.User;
import com.bookflix.bookflix.user.entity.enumType.BookStatus;
import com.bookflix.bookflix.user.repository.HistoryRepository;
import com.bookflix.bookflix.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    @Override
    public void createAndAddHistory(User user, String isbn){
        Book book = bookRepository.findById(isbn)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.BOOK_NOT_EXIST));
        History history = historyRepository.save(new History(user, book));
        user.addHistory(history);
    }

    @Override
    public GetBorrowHistoryRes getBorrowHistory(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_EXIST));

        List<BorrowHistoryInfo> borrowHistoryInfoList = historyRepository.findByUserAndbookStatus(user, BookStatus.BORROW)
                .stream().map(History::getBook).map(BorrowHistoryInfo::of).collect(Collectors.toList());
        return new GetBorrowHistoryRes(borrowHistoryInfoList);
    }
}
