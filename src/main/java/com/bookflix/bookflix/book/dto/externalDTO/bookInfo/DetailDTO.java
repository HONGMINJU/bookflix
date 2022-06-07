package com.bookflix.bookflix.book.dto.externalDTO.bookInfo;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "detail")
@XmlAccessorType(XmlAccessType.FIELD)
public class DetailDTO {

    @XmlElement(name = "book")
    private BookDTO bookDTO;
}
