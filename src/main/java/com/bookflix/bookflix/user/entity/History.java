package com.bookflix.bookflix.user.entity;

import com.bookflix.bookflix.book.entity.Book;
import com.bookflix.bookflix.library.entity.Library;
import com.bookflix.bookflix.user.entity.enumType.BookStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class History {

    @Column(name = "HISTORY_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ISBN")
    private Book book;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    public History(User user, Book book){
        this.user = user;
        this.book = book;
        this.bookStatus = BookStatus.READ;
    }
}
