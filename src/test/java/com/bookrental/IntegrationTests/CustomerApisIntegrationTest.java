package com.bookrental.IntegrationTests;

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
        Book book = new Book("zyTCAlFPjgYC",
                "The Google Story",
                Set.of(new Author("David A. Vise"),new Author("Mark Malseed")),
                Set.of(new Category("Business & Economics / Entrepreneurship"), new Category("Computers / Information Technology"), new Category("History / Modern / 20th Century")),
                "\"Here is the story behind one of the most remarkable Internet successes of our time. Based on scrupulous research and extraordinary access to Google",
                "http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=1&imgtk=AFLRE70pwzDs7di2l10gDISoQ9eAYlDDUJS1_RaIoansAZS2oPOHM0lgxj2OLvAb1NL0SVyx1pSOFLfv42uuMYlhZ-obp-C4UmDKrFfSFa-0s-WQ-7qSDdxOPwZOueRQcl-W6enWgsKr&source=gbs_api",
                "http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=5&imgtk=AFLRE72jG1eJOZ2lnI3TNwKGK8uSV3h7Igy_0ObYNp5SbXjTlnYmPGxPA9joI2RSyx8AYuk56AmWnbA6NAAI4PxkdUNe6-5iLtoHUBp2abMknLzLezbbjCR8nXw2qaLb0qRPgVs2z9eC&source=gbs_api",
                "Random House Publishing Group",
                "2005-11-15",
                50,
                2,
                3.5,
                20);
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
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createUrlWithPort("/Book/zyTCAlFPjgYC/Order"), HttpMethod.PUT,
                entity, String.class);

        //Assert
        Assert.assertEquals(copy, responseEntity.getBody());
    }

    @Test
    public void ShouldAbleToReturnParticularCopyOfTheBook(){
        //Arrange
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        //Act
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createUrlWithPort("/Book/Copy/zyTCAlFPjgYC:1/Return"), HttpMethod.PUT,
                entity, String.class);

        //Assert
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    private String createUrlWithPort(String api) {
        return "http://localhost:"+port+api;
    }
}
