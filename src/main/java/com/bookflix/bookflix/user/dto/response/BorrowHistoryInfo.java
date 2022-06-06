package com.bookflix.bookflix.user.dto.response;

import com.bookflix.bookflix.book.entity.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.IdClass;

@Getter
@NoArgsConstructor
public class BorrowHistoryInfo {

    private String ISBN;
    private String title;
    private String posterURL;
    private int publishYear;
    private String summary;

    @Builder
    public BorrowHistoryInfo(String ISBN, String title, String posterURL, int publishYear, String summary) {
        this.ISBN = ISBN;
        this.title = title;
        this.posterURL = posterURL;
        this.publishYear = publishYear;
        this.summary = summary;
    }

    public static BorrowHistoryInfo of(Book book){
        return BorrowHistoryInfo.builder()
                .ISBN(book.getId())
                .title(book.getTitle())
                .posterURL(book.getCoverURL())
                .publishYear(book.getYear())
                .summary(book.getInfo())
                .build();
    }
}
