package com.codetest.book.book;

import org.springframework.stereotype.Service;

//create instance of class -> passing book operation to BookRepository

@Service
public class BookService {

    BookRepository bookRepository;

    //create constructor
    // I am using service annotation here which means an instance from this class will be generated by spring and when spring creates an instance of this class it will be calling this constructor

    public BookService(BookRepository bookRepository){
        super();
        this.bookRepository = bookRepository;
    }

    //save book to database
    public Book save (Book book){
       return  bookRepository.save(book);

    }
}
