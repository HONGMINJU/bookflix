package com.bookflix.bookflix.book.dto.response;

import com.bookflix.bookflix.book.entity.Book;
import com.bookflix.bookflix.library.entity.NearLibrary;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SimilarBookInfo {

    private String ISBN;
    private String title;
    private String posterURL;

    @Builder
    public SimilarBookInfo(String ISBN, String title, String posterURL){
        this.ISBN = ISBN;
        this.title = title;
        this.posterURL = posterURL;
    }

    public static SimilarBookInfo of(Book book){
        return SimilarBookInfo.builder()
                .ISBN(book.getId())
                .title(book.getTitle())
                .posterURL(book.getCoverURL())
                .build();
    }
}
