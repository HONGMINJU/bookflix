package com.bookflix.bookflix.book.dto.externalDTO.bookInfo;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookInfoResponseDTO {

    @XmlElement(name = "request")
    private BookInfoRequestDTO request;

    @XmlElement(name = "detail")
    private DetailDTO detailDTO;
}
