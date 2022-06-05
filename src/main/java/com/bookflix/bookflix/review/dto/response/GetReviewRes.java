package com.bookflix.bookflix.review.dto.response;

import com.bookflix.bookflix.review.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetReviewRes {

    private int score;
    private String contents;
    private String title;
    private String posterURL;

    @Builder
    public GetReviewRes(int score, String contents, String title, String posterURL) {
        this.score = score;
        this.contents = contents;
        this.title = title;
        this.posterURL = posterURL;
    }

    public static GetReviewRes of(Review review){
        return GetReviewRes.builder()
                .score(review.getScore())
                .contents(review.getContents())
                .title(review.getBook().getTitle())
                .posterURL(review.getBook().getCoverURL())
                .build();
    }
}
