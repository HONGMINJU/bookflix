package com.bookflix.bookflix.book.dto.response;

import com.bookflix.bookflix.book.entity.Book;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetBookInfoRes {
    private String title;
    private String posterURL;
    private int publishYear;
    private String isbn;
    private String summary;

    @Builder
    public GetBookInfoRes(String title, String posterURL, int publishYear, String isbn, String summary){
        this.title = title;
        this.posterURL = posterURL;
        this.publishYear = publishYear;
        this.isbn = isbn;
        this.summary = summary;
    }

    public static GetBookInfoRes of(Book book) {
        return GetBookInfoRes.builder()
                .title(book.getTitle())
                .posterURL(book.getCoverURL())
                .publishYear(book.getYear())
                .isbn(book.getId())
                .summary(book.getInfo())
                .build();
    }
}
