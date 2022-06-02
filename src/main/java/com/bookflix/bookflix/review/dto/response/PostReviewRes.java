package com.bookflix.bookflix.review.dto.response;

import com.bookflix.bookflix.review.entity.Review;
import com.bookflix.bookflix.user.dto.response.PostUserRes;
import com.bookflix.bookflix.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostReviewRes {

    private Long reviewId;

    @Builder
    public PostReviewRes(Long reviewId) {
        this.reviewId = reviewId;
    }

    public static PostReviewRes of(Review review){
        return PostReviewRes.builder()
                .reviewId(review.getId())
                .build();
    }
}
