package com.bookflix.bookflix.book.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetSimilarBookListRes {
    List<BookInfo> bookList;
}
