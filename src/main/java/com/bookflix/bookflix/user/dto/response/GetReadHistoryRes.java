package com.bookflix.bookflix.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetReadHistoryRes {
    private List<ReadHistoryInfo> bookList;
}
