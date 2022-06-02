package com.bookflix.bookflix.review.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostReviewReq {

    @NotBlank
    private String isbn;

    @PositiveOrZero
    private int score;

    @NotBlank
    private String contents;
}
