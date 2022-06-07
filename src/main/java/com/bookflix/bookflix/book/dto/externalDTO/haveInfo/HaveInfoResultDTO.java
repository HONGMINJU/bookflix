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
@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
public class HaveInfoResultDTO {

    @XmlElement(name = "hasBook")
    private String hasBook;

    @XmlElement(name = "loanAvailable")
    private String loanAvailable;
}
