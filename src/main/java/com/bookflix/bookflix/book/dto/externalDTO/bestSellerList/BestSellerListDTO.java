package com.bookflix.bookflix.book.dto.externalDTO.bestSellerList;

import com.bookflix.bookflix.book.dto.externalDTO.bookInfo.BookInfoRequestDTO;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class BestSellerListDTO {

    @XmlElement(name = "request")
    private BestSellerRequestDTO request;

    @XmlElement(name = "regionNm")
    private String regionNm;

    @XmlElement(name = "resultNum")
    private int resultNum;

    @XmlElementWrapper(name="docs")
    @XmlElement(name = "doc")
    private List<BestSellerDTO> bestSellerDTOList;
}
