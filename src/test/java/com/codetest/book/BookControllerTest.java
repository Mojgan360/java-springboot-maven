package com.codetest.book;

import com.codetest.book.book.Book;
import com.codetest.book.book.BookRepository;
import com.codetest.book.shared.GenericResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //This will be initializing the application for our test environment which means a web server will be started by default.
@ActiveProfiles("test")  //This gives the flexibility of configurable runtime behavior for the application.
public class BookControllerTest {
    private static final String API_1_0_BOOKS = "/api/1.0/books";

//When is running: this Springboot test, create an application context and that context contain an instance of this testRestTemplate.
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    BookRepository bookRepository;

    @Before
    public void cleanup() {
    bookRepository.deleteAll();
}

    @Test
    public void postBook_whenBookIsCreated_receivedOk()  {
        Book book = createBook();

        ResponseEntity<Object> response = postBook(book, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void postBook_whenBookCreated_savedBookInDatabase () {
        Book book = createBook();

        postBook( book, Object.class);
       assertThat(bookRepository.count()).isEqualTo(1);
    }

    @Test
    public void postBook_whenBookIsCreated_receivedSuccessMessage()  {
        Book book = createBook();

        ResponseEntity<GenericResponse> response = postBook(book, GenericResponse.class);
        assertThat(response.getBody().getMessage()).isNotNull();
    }

    @Test
    public void postBook_whenBookHasNotAllTitle_receiveVadRequesr() {
        Book book = createBook();
        book.setTitle(null);
        ResponseEntity<Object> response =  postBook(book,Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }
    @Test
    public void postBook_whenBookHasNotAllAutor_receiveVadRequesr() {
        Book book = createBook();
        book.setAuthor(null);
        ResponseEntity<Object> response =  postBook(book,Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }
    @Test
    public void postBook_whenBookHasNotAllGenre_receiveVadRequesr() {
        Book book = createBook();
        book.setGenre(null);
        ResponseEntity<Object> response =  postBook(book,Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }
    @Test
    public void postBook_whenBookHasNotAllPrice_receiveVadRequesr() {
        Book book = createBook();
        book.setPrice(null);
        ResponseEntity<Object> response =  postBook(book,Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }
    @Test
    public void postBook_whenBookHasNotAllDate_receiveVadRequesr() {
        Book book = createBook();
        book.setPublish_date(null);
        ResponseEntity<Object> response =  postBook(book,Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }
    @Test
    public void postBook_whenBookHasNotAllDescription_receiveVadRequesr() {
        Book book = createBook();
        book.setDescription(null);
        ResponseEntity<Object> response =  postBook(book,Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    public<T> ResponseEntity<T> postBook(Object request, Class<T> response) {
    	return testRestTemplate.postForEntity(API_1_0_BOOKS  , request,response);
    }
    
    
    
    private Book createBook() {
        Book book = new Book();
        book.setId("B1");
		book.setAuthor("author");
		book.setTitle("title");
		book.setGenre("Genre");
		book.setPrice(22.22);
		book.setPublish_date("22.02.22");
		book.setDescription("A former architect battles corporate zombies, an evil sorceress, a");
        
        
        return book;
    }
}
