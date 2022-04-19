package com.codetest.book.book;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity //changes Book class to database entity -> mapping en object to database table
public class Book {

    @Id //log id is primary kay
    @GeneratedValue //Create Primary Key
    private Long idbook;
	private String id;
	private String author;
	private String title;
	private String genre;
	private Double price;
	private String publish_date;
	private String description;
}
