package com.bookflix.bookflix.book.dto.xml.haveInfo;

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
public class responseDTO {

    @XmlElement(name = "request")
    private com.bookflix.bookflix.book.dto.xml.haveInfo.requestDTO requestDTO;

    @XmlElement(name = "result")
    private com.bookflix.bookflix.book.dto.xml.haveInfo.resultDTO resultDTO;
}
