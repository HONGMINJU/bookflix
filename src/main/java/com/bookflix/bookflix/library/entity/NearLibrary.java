package com.bookflix.bookflix.library.entity;

import com.bookflix.bookflix.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class NearLibrary {
    @Column(name = "NEAR_LIBRARY_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "LIBRARY_ID")
    private Library library;

    public NearLibrary(User user, Library library){
        this.user = user;
        this.library = library;
    }
}
