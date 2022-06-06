package com.bookflix.bookflix.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetBorrowHistoryRes {
    private List<BorrowHistoryInfo> bookList;
}
