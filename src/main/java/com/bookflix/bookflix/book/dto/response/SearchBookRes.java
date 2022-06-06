package com.bookflix.bookflix.book.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SearchBookRes {

    private List<GetBookInfoRes> bookList;

    @Builder
    public SearchBookRes(List<GetBookInfoRes> bookList){
        this.bookList = bookList;
    }
}
