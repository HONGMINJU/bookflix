package com.bookflix.bookflix.book.dto.externalDTO.bestSellerList;


import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@XmlRootElement(name = "doc")
@XmlAccessorType(XmlAccessType.FIELD)
public class BestSellerDTO {

    @XmlElement(name = "no")
    private int no;

    @XmlElement(name = "ranking")
    private int ranking;

    @XmlElement(name = "bookname")
    private String bookname;

    @XmlElement(name = "authors")
    private String authors;

    @XmlElement(name = "publisher")
    private String publisher;

    @XmlElement(name = "vol")
    private String vol;

    @XmlElement(name = "publication_year")
    private int publication_year;

    @XmlElement(name = "isbn13")
    private String isbn13;

    @XmlElement(name = "addition_symbol")
    private int addition_symbol;

    @XmlElement(name = "class_no")
    private float class_no;

    @XmlElement(name = "class_nm")
    private String class_nm;

    @XmlElement(name = "loan_count")
    private int loan_count;

    @XmlElement(name = "bookImageURL")
    private String bookImageURL;
}
