package com.bookflix.bookflix.book.entity;

import lombok.AccessLevel;
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
}
