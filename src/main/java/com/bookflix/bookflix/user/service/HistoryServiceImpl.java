package com.bookflix.bookflix.user.service;

import com.bookflix.bookflix.book.entity.Book;
import com.bookflix.bookflix.book.repository.BookRepository;
import com.bookflix.bookflix.common.response.BaseException;
import com.bookflix.bookflix.common.response.BaseResponseStatus;
import com.bookflix.bookflix.review.repository.ReviewRepository;
import com.bookflix.bookflix.user.dto.response.BorrowHistoryInfo;
import com.bookflix.bookflix.user.dto.response.GetBorrowHistoryRes;
import com.bookflix.bookflix.user.dto.response.GetReadHistoryRes;
import com.bookflix.bookflix.user.dto.response.ReadHistoryInfo;
import com.bookflix.bookflix.user.entity.History;
import com.bookflix.bookflix.user.entity.User;
import com.bookflix.bookflix.user.entity.enumType.BookStatus;
import com.bookflix.bookflix.user.repository.HistoryRepository;
import com.bookflix.bookflix.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    private final ReviewRepository reviewRepository;

    private final RecommendService recommendService;

    @Override
    @Transactional
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

        List<BorrowHistoryInfo> borrowHistoryInfoList = historyRepository.findHistoriesByUserAndBookStatus(user, BookStatus.BORROW)
                .stream().map(History::getBook).map(BorrowHistoryInfo::of).collect(Collectors.toList());
        return new GetBorrowHistoryRes(borrowHistoryInfoList);
    }

    @Override
    public GetReadHistoryRes getReadHistory(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_EXIST));

        List<History> readHistory = historyRepository.findHistoriesByUserAndBookStatus(user, BookStatus.READ);
        List<ReadHistoryInfo> readHistoryInfoList = new ArrayList<>();
        for (History history : readHistory){
            readHistoryInfoList.add(ReadHistoryInfo.of(history.getBook(),
                    reviewRepository.existsByWriterAndBook(user, history.getBook())));
        }
        return new GetReadHistoryRes(readHistoryInfoList);
    }

    @Override
    @Transactional
    public void postReadHistory(Long userId, String isbn){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.USER_NOT_EXIST));
        Book book = bookRepository.findById(isbn)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.BOOK_NOT_EXIST));
        History history = historyRepository.save(new History(user, book));
        List<String> isbnList = historyRepository.findHistoriesByUserAndBookStatus(user, BookStatus.READ)
                        .stream().map(History::getBook).map(Book::getId).collect(Collectors.toList());
        recommendService.setRecommendList(user, isbnList);
    }
}
