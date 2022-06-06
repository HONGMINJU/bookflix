package com.bookflix.bookflix.book.dto.externalDTO.haveInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class requestDTO {

    @XmlElement(name = "isbn13")
    private String isbn13;

    @XmlElement(name = "libCode")
    private String libCode;
}
