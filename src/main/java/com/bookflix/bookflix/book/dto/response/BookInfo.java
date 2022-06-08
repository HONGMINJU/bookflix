package com.bookflix.bookflix.book.dto.response;

import com.bookflix.bookflix.book.entity.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookInfo {

    private String ISBN;
    private String title;
    private String posterURL;

    @Builder
    public BookInfo(String ISBN, String title, String posterURL){
        this.ISBN = ISBN;
        this.title = title;
        this.posterURL = posterURL;
    }

    public static BookInfo of(Book book){
        return BookInfo.builder()
                .ISBN(book.getId())
                .title(book.getTitle())
                .posterURL(book.getCoverURL())
                .build();
    }
}
