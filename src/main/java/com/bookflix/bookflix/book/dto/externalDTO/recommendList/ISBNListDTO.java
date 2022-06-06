package com.bookflix.bookflix.book.dto.externalDTO.recommendList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ISBNListDTO {
    private List<String> isbn;
}
