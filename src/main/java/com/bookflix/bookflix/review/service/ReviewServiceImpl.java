package com.bookflix.bookflix.review.service;

import com.bookflix.bookflix.book.entity.Book;
import com.bookflix.bookflix.book.repository.BookRepository;
import com.bookflix.bookflix.common.response.BaseException;
import com.bookflix.bookflix.common.response.BaseResponseStatus;
import com.bookflix.bookflix.review.dto.request.PostReviewReq;
import com.bookflix.bookflix.review.dto.response.PostReviewRes;
import com.bookflix.bookflix.review.entity.Review;
import com.bookflix.bookflix.review.repository.ReviewRepository;
import com.bookflix.bookflix.user.entity.User;
import com.bookflix.bookflix.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    @Override
    public PostReviewRes createReview(Long userId, PostReviewReq postReviewReq){

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.USER_NOT_EXIST));
        Book book = bookRepository.findById(postReviewReq.getIsbn())
                .orElseThrow(()-> new BaseException(BaseResponseStatus.BOOK_NOT_EXIST));
        Review review = reviewRepository.save(new Review(user, book, postReviewReq.getScore(), postReviewReq.getContents()));

        return PostReviewRes.of(review);
    }
}
