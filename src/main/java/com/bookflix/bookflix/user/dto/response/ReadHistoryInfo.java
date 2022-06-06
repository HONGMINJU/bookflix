package com.bookflix.bookflix.user.dto.response;

import com.bookflix.bookflix.book.entity.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReadHistoryInfo {

    private String ISBN;
    private String title;
    private String posterURL;
    private int publishYear;
    private String summary;
    private boolean isReviewed;

    @Builder
    public ReadHistoryInfo(String ISBN, String title, String posterURL, int publishYear, String summary, boolean isReviewed) {
        this.ISBN = ISBN;
        this.title = title;
        this.posterURL = posterURL;
        this.publishYear = publishYear;
        this.summary = summary;
        this.isReviewed = isReviewed;
    }

    public static ReadHistoryInfo of(Book book, boolean isReviewed){
        return ReadHistoryInfo.builder()
                .ISBN(book.getId())
                .title(book.getTitle())
                .posterURL(book.getCoverURL())
                .publishYear(book.getYear())
                .summary(book.getInfo())
                .isReviewed(isReviewed)
                .build();
    }
}
