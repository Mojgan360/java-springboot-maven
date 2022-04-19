package com.codetest.book.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codetest.book.shared.GenericResponse;

//m

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/api/1.0/books")
    GenericResponse createBook (@RequestBody Book book) {
    	if(book.getTitle() == null || book.getAuthor() == null || book.getGenre() == null || book.getPrice() == null || book.getPublish_date() == null || book.getDescription() == null) {
    		throw new BookNotValidExeption();
    	}
        bookService.save(book);
        return  new GenericResponse("Book saved.");
    }
}

