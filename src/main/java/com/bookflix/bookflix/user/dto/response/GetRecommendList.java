package com.bookflix.bookflix.user.dto.response;

import com.bookflix.bookflix.book.dto.response.BookInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetRecommendList {

    List<BookInfo> userRecommendList;
    List<BookInfo> newRecommendList;
    List<BookInfo> bestSeller;

}
