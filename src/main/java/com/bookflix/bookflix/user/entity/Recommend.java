package com.bookflix.bookflix.user.entity;

import com.bookflix.bookflix.book.entity.Book;
import com.bookflix.bookflix.user.entity.enumType.BookStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Recommend {

    @Column(name = "RECOMMEND_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ISBN")
    private Book book;

    public Recommend(User user, Book book){
        this.user = user;
        this.book = book;
    }
}
