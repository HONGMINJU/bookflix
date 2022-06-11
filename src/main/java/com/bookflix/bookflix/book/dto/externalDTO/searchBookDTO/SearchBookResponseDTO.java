package com.bookflix.bookflix.book.dto.externalDTO.searchBookDTO;

import com.bookflix.bookflix.book.dto.externalDTO.bestSellerList.BestSellerDTO;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchBookResponseDTO {

    @XmlElement(name = "request")
    private SearchRequestDTO request;

    @XmlElementWrapper(name="docs")
    @XmlElement(name = "doc")
    private List<SearchBookDTO> searchBookList;
}
