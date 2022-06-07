package com.bookflix.bookflix.book.entity;

import com.bookflix.bookflix.book.dto.externalDTO.bookInfo.BookDTO;
import com.bookflix.bookflix.book.dto.externalDTO.bookInfo.BookInfoResponseDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Book {

    @Column(name = "ISBN")
    @Id
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(name = "PUBLISH_YEAR", nullable = false)
    private int year;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String publisher;

    @Column(name = "BOOK_COVER", columnDefinition="TEXT", nullable = false)
    private String coverURL;

    @Column(columnDefinition="TEXT", nullable = false)
    private String info;

    @Builder
    public Book(String isbn, String title, int year, String writer,
                String publisher, String coverURL, String info){
        this.id = isbn;
        this.title = title;
        this.year = year;
        this.writer = writer;
        this.publisher = publisher;
        this.coverURL = coverURL;
        this.info = info;
    }

    public static Book of(BookInfoResponseDTO bookInfoResponseDTO){
        BookDTO bookDTO = bookInfoResponseDTO.getDetailDTO().getBookDTO();
        return Book.builder()
                .isbn(bookDTO.getIsbn13())
                .title(bookDTO.getBookname())
                .year(bookDTO.getPublication_year())
                .writer(bookDTO.getAuthors())
                .publisher(bookDTO.getPublisher())
                .coverURL(bookDTO.getBookImageURL())
                .info(bookDTO.getDescription())
                .build();
    }
}
