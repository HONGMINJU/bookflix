package com.bookflix.bookflix.book.dto.externalDTO.bookInfo;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "book")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookDTO {

    @XmlElement(name = "no")
    private int no;

    @XmlElement(name = "bookname")
    private String bookname;

    @XmlElement(name = "authors")
    private String authors;

    @XmlElement(name = "publisher")
    private String publisher;

    @XmlElement(name = "publication_year")
    private int publication_year;

    @XmlElement(name = "bookImageURL")
    private String bookImageURL;

    @XmlElement(name = "isbn")
    private String isbn;

    @XmlElement(name = "isbn13")
    private String isbn13;

    @XmlElement(name = "description")
    private String description;
}
