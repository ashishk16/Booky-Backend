package com.bookrental.IntegrationTests;

import com.bookrental.BookApiApplication;
import com.bookrental.Models.Author;
import com.bookrental.Models.Book;
import com.bookrental.Models.Category;
import com.bookrental.Repositories.AuthorRepository;
import com.bookrental.Repositories.BookRepository;
import com.bookrental.Repositories.CategoryRepository;
import com.bookrental.Services.BookService;
import org.json.JSONException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookApiIntegrationTest {
    @Autowired
    BookService bookService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    @LocalServerPort
    private int port;

    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    private HttpHeaders headers = new HttpHeaders();

//    EmbeddedDatabase db;
//
//    public BookApiIntegrationTest() {
//        db = new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .build();
//    }

    @Before
    public void setup(){
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
        authorRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    public void shouldReturnListOfAllBooks() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createURLWithPort("/Book"), HttpMethod.GET,
                entity, String.class);

        String books = "[{\"id\":\"zyTCAlFPjgYC\"," +
                "\"title\":\"The Google Story\"," +
//                "\"authors\":[{\"id\":1,\"name\":\"David A. Vise\"},{\"id\":2,\"name\":\"Mark Malseed\"}]," +
//                "\"categories\":[{\"id\":1,\"name\":\"Business & Economics / Entrepreneurship\"},{\"id\":2,\"name\":\"Computers / Information Technology\"},{\"id\":3,\"name\":\"History / Modern / 20th Century\"}]," +
                "\"description\":\"\\\"Here is the story behind one of the most remarkable Internet successes of our time. Based on scrupulous research and extraordinary access to Google\"," +
                "\"thumbnail\":\"http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=1&imgtk=AFLRE70pwzDs7di2l10gDISoQ9eAYlDDUJS1_RaIoansAZS2oPOHM0lgxj2OLvAb1NL0SVyx1pSOFLfv42uuMYlhZ-obp-C4UmDKrFfSFa-0s-WQ-7qSDdxOPwZOueRQcl-W6enWgsKr&source=gbs_api\"," +
                "\"smallThumbnail\":\"http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=5&imgtk=AFLRE72jG1eJOZ2lnI3TNwKGK8uSV3h7Igy_0ObYNp5SbXjTlnYmPGxPA9joI2RSyx8AYuk56AmWnbA6NAAI4PxkdUNe6-5iLtoHUBp2abMknLzLezbbjCR8nXw2qaLb0qRPgVs2z9eC&source=gbs_api\"," +
                "\"publisher\":\"Random House Publishing Group\"," +
                "\"publishedDate\":\"2005-11-15\"," +
                "\"price\":50," +
                "\"noOfCopies\":2," +
                "\"averageRating\":3.5," +
                "\"ratingCount\":20}]";

        JSONAssert.assertEquals(books, responseEntity.getBody(), false);
    }

    @Test
    public void shouldReturnBookById() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createURLWithPort("/Book/zyTCAlFPjgYC"), HttpMethod.GET,
                entity, String.class);

        String book = "{\"id\":\"zyTCAlFPjgYC\"," +
                "\"title\":\"The Google Story\"," +
//                "\"authors\":[{\"id\":1,\"name\":\"David A. Vise\"},{\"id\":2,\"name\":\"Mark Malseed\"}]," +
//                "\"categories\":[{\"id\":1,\"name\":\"Business & Economics / Entrepreneurship\"},{\"id\":2,\"name\":\"Computers / Information Technology\"},{\"id\":3,\"name\":\"History / Modern / 20th Century\"}]," +
                "\"description\":\"\\\"Here is the story behind one of the most remarkable Internet successes of our time. Based on scrupulous research and extraordinary access to Google\"," +
                "\"thumbnail\":\"http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=1&imgtk=AFLRE70pwzDs7di2l10gDISoQ9eAYlDDUJS1_RaIoansAZS2oPOHM0lgxj2OLvAb1NL0SVyx1pSOFLfv42uuMYlhZ-obp-C4UmDKrFfSFa-0s-WQ-7qSDdxOPwZOueRQcl-W6enWgsKr&source=gbs_api\"," +
                "\"smallThumbnail\":\"http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=5&imgtk=AFLRE72jG1eJOZ2lnI3TNwKGK8uSV3h7Igy_0ObYNp5SbXjTlnYmPGxPA9joI2RSyx8AYuk56AmWnbA6NAAI4PxkdUNe6-5iLtoHUBp2abMknLzLezbbjCR8nXw2qaLb0qRPgVs2z9eC&source=gbs_api\"," +
                "\"publisher\":\"Random House Publishing Group\"," +
                "\"publishedDate\":\"2005-11-15\"," +
                "\"price\":50," +
                "\"noOfCopies\":2," +
                "\"averageRating\":3.5," +
                "\"ratingCount\":20}";

        JSONAssert.assertEquals(book, responseEntity.getBody(), false);

    }

    @Test
    public void shouldReturnCREATEDHttpStatusCodeOnSuccessfulInsertionOfNewBook(){
        //Arrange
        Book newBook = new Book("zyTCAlFPjgYf",
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
        HttpEntity<Book> entity = new HttpEntity<Book>(newBook, headers);

        //Act
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createURLWithPort("/Book"),
                HttpMethod.POST, entity, String.class);

        //Assert
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }

    @Test
    public void shouldReturnBADREQUESTHttpStatusCodeOnInvalidateJsonToInsertBook(){
        //Arrange
        Book newBook = new Book();
        HttpEntity<Book> entity = new HttpEntity<Book>(newBook, headers);

        //Act
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createURLWithPort("/Book"),
                HttpMethod.POST, entity, String.class);

        //Assert
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void shouldReturn200HttpStatusCodeOnSuccessfulDeletionOfBook(){
        //Arrange
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        //Act
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createURLWithPort("/Book/zyTCAlFPjgYC"),
                HttpMethod.DELETE, entity, String.class);

        //Assert
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void shouldReturn404HttpStatusCodeWhenBookToBeDeletedNotFound(){
        //Arrange
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        //Act
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(createURLWithPort("/Book/zyTCAlFPjgYg"),
                HttpMethod.DELETE, entity, String.class);

        //Assert
        Assert.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    private String createURLWithPort(String api) {
        return "http://localhost:"+port+api;
    }
}
