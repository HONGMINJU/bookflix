package com.bookflix.bookflix.review.service;

import com.bookflix.bookflix.book.entity.Book;
import com.bookflix.bookflix.book.repository.BookRepository;
import com.bookflix.bookflix.common.response.BaseException;
import com.bookflix.bookflix.common.response.BaseResponseStatus;
import com.bookflix.bookflix.review.dto.request.PostReviewReq;
import com.bookflix.bookflix.review.dto.request.PutReviewReq;
import com.bookflix.bookflix.review.dto.response.GetReviewRes;
import com.bookflix.bookflix.review.dto.response.PostReviewRes;
import com.bookflix.bookflix.review.entity.Review;
import com.bookflix.bookflix.review.repository.ReviewRepository;
import com.bookflix.bookflix.user.entity.User;
import com.bookflix.bookflix.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.bookflix.bookflix.common.response.BaseResponseStatus.REVIEW_NOT_WRITER;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    @Override
    public GetReviewRes getReview(Long userId, Long reviewId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.USER_NOT_EXIST));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.REVIEW_NOT_EXIST));
        if(review.getWriter().getId() != user.getId())
            throw new BaseException(REVIEW_NOT_WRITER);
        return GetReviewRes.of(review);
    }

    @Override
    public PostReviewRes createReview(Long userId, PostReviewReq postReviewReq){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.USER_NOT_EXIST));
        Book book = bookRepository.findById(postReviewReq.getIsbn())
                .orElseThrow(()-> new BaseException(BaseResponseStatus.BOOK_NOT_EXIST));
        Review review = reviewRepository.save(new Review(user, book, postReviewReq.getScore(), postReviewReq.getContents()));

        return PostReviewRes.of(review);
    }

    @Override
    @Transactional
    public void updateReview(Long userId, Long reviewId, PutReviewReq putReviewReq){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.USER_NOT_EXIST));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.REVIEW_NOT_EXIST));
        if(review.getWriter().getId() != userId)
            throw new BaseException(REVIEW_NOT_WRITER);
        review.updateReview(putReviewReq.getScore(), putReviewReq.getContents());
        return;
    }

    @Override
    public void deleteReview(Long userId, Long reviewId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.USER_NOT_EXIST));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.REVIEW_NOT_EXIST));
        if(review.getWriter().getId() != user.getId())
            throw new BaseException(REVIEW_NOT_WRITER);
        reviewRepository.delete(review);
        return;
    }
}
