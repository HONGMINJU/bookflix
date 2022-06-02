package com.bookflix.bookflix.review.entity;

import com.bookflix.bookflix.book.entity.Book;
import com.bookflix.bookflix.common.entity.BaseTimeEntity;
import com.bookflix.bookflix.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review extends BaseTimeEntity {

    @Column(name = "REVIEW_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    private User writer;

    @ManyToOne
    private Book book;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private String contents;

    public Review(User writer, Book book, int score, String contents){
        this.writer = writer;
        this.book = book;
        this.score = score;
        this.contents = contents;
    }

    public void updateReview(int score, String contents){
        this.score = score;
        this.contents = contents;
    }
}
