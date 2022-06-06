package com.bookflix.bookflix.book.dto.response;

import com.bookflix.bookflix.book.entity.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetBookRes {
    private String title;
    private String posterURL;
    private int publishYear;
    private String writer;
    private String publishCompany;
    private String isbn;
    private String summary;
    private List<LibraryInfo> nearLibrary;

    @Builder
    public GetBookRes(String title, String posterURL, int publishYear, String writer, String publishCompany,
                      String isbn, String summary, List<LibraryInfo> nearLibrary){
        this.title = title;
        this.posterURL = posterURL;
        this.publishYear = publishYear;
        this.writer = writer;
        this.publishCompany = publishCompany;
        this.isbn = isbn;
        this.summary = summary;
        this.nearLibrary = nearLibrary;
    }

    public static GetBookRes of(Book book, List<LibraryInfo> nearLibrary) {
        return GetBookRes.builder()
                .title(book.getTitle())
                .posterURL(book.getCoverURL())
                .publishYear(book.getYear())
                .writer(book.getWriter())
                .publishCompany(book.getPublisher())
                .isbn(book.getId())
                .summary(book.getInfo())
                .nearLibrary(nearLibrary)
                .build();
    }
}
