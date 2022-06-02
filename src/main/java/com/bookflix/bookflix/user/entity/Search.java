package com.bookflix.bookflix.user.entity;

import com.bookflix.bookflix.common.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Search extends BaseTimeEntity {

    @Column(name = "SEARCH_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    private User searcher;

    @Column(nullable = false)
    private String keyword;
}
