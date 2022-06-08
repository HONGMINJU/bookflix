package com.bookflix.bookflix.book.dto.externalDTO.bestSellerList;

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
public class BestSellerRequestDTO {

    @XmlElement(name = "age")
    private int age;

    @XmlElement(name = "region")
    private int region;

    @XmlElement(name = "pageNo")
    private int pageNo;

    @XmlElement(name = "pageSize")
    private int pageSize;
}
