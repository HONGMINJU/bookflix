package com.bookflix.bookflix.book.dto.externalDTO.searchBookDTO;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchRequestDTO {

    @XmlElement(name = "keyword")
    private String keyword;

    @XmlElement(name = "pageNo")
    private int pageNo;

    @XmlElement(name = "pageSize")
    private int pageSize;
}
