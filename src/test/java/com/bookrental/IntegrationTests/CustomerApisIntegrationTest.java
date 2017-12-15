package com.bookrental.IntegrationTests;

import com.bookrental.BookBuilder.BookBuilder;
import com.bookrental.Models.Author;
import com.bookrental.Models.Book;
import com.bookrental.Models.Category;
import com.bookrental.Repositories.AuthorRepository;
import com.bookrental.Repositories.BookRepository;
import com.bookrental.Repositories.CategoryRepository;
import com.bookrental.Repositories.CopyRepository;
import com.bookrental.Services.BookService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerApisIntegrationTest {

    @LocalServerPort
    private int port;
    private TestRestTemplate testRestTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Autowired
    BookService bookService;
    @Autowired
    CopyRepository copyRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BookRepository bookRepository;

    @Before
    public void setUp(){
        Book book = new BookBuilder().setNoOfCopies(2).buildBook();
        bookService.add(book);
    }

    @After
    public void cleanUp(){
        bookRepository.deleteAll();
        copyRepository.deleteAll();
        authorRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    public void ShouldOrderACopyOfBookAndReturnThatCopy(){
        //Arrange
        String copy = "{\"id\":\"zyTCAlFPjgYC:1\"," +
                "\"status\":\"Rented\"}";
        HttpEntity<String> entity  = new HttpEntity<>(null, headers);

        //Act
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createUrlWithPort("/books/zyTCAlFPjgYC/order"), HttpMethod.PUT,
                entity, String.class);

        //Assert
        Assert.assertEquals(copy, responseEntity.getBody());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void ShouldReturn200HttpStatusCodeWhenParticularCopyOfTheBookIsSuccessfullyReturned(){
        //Arrange
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        //Act
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createUrlWithPort("/books/copies/zyTCAlFPjgYC:1/return"), HttpMethod.PUT,
                entity, String.class);

        //Assert
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void ShouldReturn404HttpStatusCodeWhenParticularCopyOfTheBookToBeReturnedIsNotFound(){
        //Arrange
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        //Act
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createUrlWithPort("/books/copies/zyTCAlFPjgYC:20/return"), HttpMethod.PUT,
                entity, String.class);

        //Assert
        Assert.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    private String createUrlWithPort(String api) {
        return "http://localhost:"+port+api;
    }
}
