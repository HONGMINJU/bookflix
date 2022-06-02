package com.bookflix.bookflix.review.controller;

import com.bookflix.bookflix.common.response.BaseResponse;
import com.bookflix.bookflix.common.service.JwtService;
import com.bookflix.bookflix.review.dto.request.PostReviewReq;
import com.bookflix.bookflix.review.dto.request.PutReviewReq;
import com.bookflix.bookflix.review.dto.response.PostReviewRes;
import com.bookflix.bookflix.review.service.ReviewService;
import com.bookflix.bookflix.user.dto.response.PostUserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    private final JwtService jwtService;

    @PostMapping("")
    public BaseResponse<PostReviewRes> postReview(@Valid @RequestBody PostReviewReq postReviewReq) {
        Long userId = jwtService.getUserIdx();
        PostReviewRes postReviewRes = reviewService.createReview(userId, postReviewReq);
        return new BaseResponse<>(postReviewRes);
    }

    @PutMapping("/{id}")
    public BaseResponse<PostReviewRes> postReview(@PathVariable("id") Long id, @Valid @RequestBody PutReviewReq putReviewReq) {
        Long userId = jwtService.getUserIdx();
        reviewService.updateReview(userId, id, putReviewReq);
        return new BaseResponse<>(null);
    }
}
