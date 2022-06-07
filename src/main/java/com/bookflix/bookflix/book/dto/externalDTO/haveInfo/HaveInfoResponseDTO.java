package com.bookflix.bookflix.book.dto.externalDTO.haveInfo;

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
public class HaveInfoResponseDTO {

    @XmlElement(name = "request")
    private HaveInfoRequestDTO requestDTO;

    @XmlElement(name = "result")
    private HaveInfoResultDTO resultDTO;
}
