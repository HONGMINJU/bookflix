package com.bookflix.bookflix.review.service;

import com.bookflix.bookflix.review.dto.request.PostReviewReq;
import com.bookflix.bookflix.review.dto.request.PutReviewReq;
import com.bookflix.bookflix.review.dto.response.GetReviewRes;
import com.bookflix.bookflix.review.dto.response.PostReviewRes;

public interface ReviewService {

    public PostReviewRes createReview(Long userId, PostReviewReq postReviewReq);
    public GetReviewRes getReview(Long userId, Long reviewId);
    public void updateReview(Long userId, Long reviewId, PutReviewReq putReviewReq);
    public void deleteReview(Long userId, Long reviewId);
}
