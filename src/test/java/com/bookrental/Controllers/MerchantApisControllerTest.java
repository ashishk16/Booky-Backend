package com.bookrental.Controllers;

import com.bookrental.Models.*;
import com.bookrental.Services.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MerchantController.class, secure = false)
public class MerchantApisControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Test
    public void getAllBooks() throws Exception {
        //Arrange
        List<Book> books = Arrays.asList(new Book("zyTCAlFPjgYC",
                "The Google Story",
                Set.of(new Author("David A. Vise"),new Author("Mark Malseed")),
                Set.of(new Category("Business & Economics / Entrepreneurship"), new Category("Computers / Information Technology"), new Category("History / Modern / 20th Century")),
                "\"Here is the story behind one of the most remarkable Internet successes of our time. Based on scrupulous research and extraordinary access to Google, the book takes you inside the creation and growth of a company whose name is a favorite brand and a standard verb recognized around the world. Its stock is worth more than General Motors’ and Ford’s combined, its staff eats for free in a dining room that used to be\u003cb\u003e \u003c/b\u003erun\u003cb\u003e \u003c/b\u003eby the Grateful Dead’s former chef, and its employees traverse the firm’s colorful Silicon Valley campus on scooters and inline skates.\u003cbr\u003e\u003cbr\u003e\u003cb\u003eTHE GOOGLE STORY \u003c/b\u003eis the definitive account of the populist media company powered by the world’s most advanced technology that in a few short years has revolutionized access to information about everything for everybody everywhere. \u003cbr\u003eIn 1998, Moscow-born Sergey Brin and Midwest-born Larry Page dropped out of graduate school at Stanford University to, in their own words, “change the world” through a search engine that would organize every bit of information on the Web for free.\u003cbr\u003e\u003cbr\u003eWhile the company has done exactly that in more than one hundred languages, Google’s quest continues as it seeks to add millions of library books, television broadcasts, and more to its searchable database. \u003cbr\u003eReaders will learn about the amazing business acumen and computer wizardry that started the company on its astonishing course; the secret network of computers delivering lightning-fast search results; the unorthodox approach that has enabled it to challenge Microsoft’s dominance and shake up Wall Street. Even as it rides high, Google wrestles with difficult choices that will enable it to continue expanding while sustaining the guiding vision of its founders’ mantra: DO NO EVIL.\"\u003cbr\u003e\u003cbr\u003e\u003cbr\u003e\u003ci\u003eFrom the Hardcover edition.\u003c/i\u003e",
                "http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=1&imgtk=AFLRE70pwzDs7di2l10gDISoQ9eAYlDDUJS1_RaIoansAZS2oPOHM0lgxj2OLvAb1NL0SVyx1pSOFLfv42uuMYlhZ-obp-C4UmDKrFfSFa-0s-WQ-7qSDdxOPwZOueRQcl-W6enWgsKr&source=gbs_api",
                "http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=5&imgtk=AFLRE72jG1eJOZ2lnI3TNwKGK8uSV3h7Igy_0ObYNp5SbXjTlnYmPGxPA9joI2RSyx8AYuk56AmWnbA6NAAI4PxkdUNe6-5iLtoHUBp2abMknLzLezbbjCR8nXw2qaLb0qRPgVs2z9eC&source=gbs_api",
                "Random House Publishing Group",
                "2005-11-15",
                50,
                10,
                3.5,
                20));

        String expectedJson = "[{\"id\":\"zyTCAlFPjgYC\"," +
                "\"title\":\"The Google Story\"," +
//                "\"authors\":[{\"id\":0,\"name\":\"David A. Vise\",\"books\":null},{\"id\":0,\"name\":\"Mark Malseed\",\"books\":null}]," +
//                "\"categories\":[{\"id\":0,\"name\":\"Business & Economics / Entrepreneurship\",\"books\":null},{\"id\":0,\"name\":\"Computers / Information Technology\",\"books\":null},{\"id\":0,\"name\":\"History / Modern / 20th Century\",\"books\":null}]," +
                "\"description\":\"\\\"Here is the story behind one of the most remarkable Internet successes of our time. Based on scrupulous research and extraordinary access to Google, the book takes you inside the creation and growth of a company whose name is a favorite brand and a standard verb recognized around the world. Its stock is worth more than General Motors’ and Ford’s combined, its staff eats for free in a dining room that used to be<b> </b>run<b> </b>by the Grateful Dead’s former chef, and its employees traverse the firm’s colorful Silicon Valley campus on scooters and inline skates.<br><br><b>THE GOOGLE STORY </b>is the definitive account of the populist media company powered by the world’s most advanced technology that in a few short years has revolutionized access to information about everything for everybody everywhere. <br>In 1998, Moscow-born Sergey Brin and Midwest-born Larry Page dropped out of graduate school at Stanford University to, in their own words, “change the world” through a search engine that would organize every bit of information on the Web for free.<br><br>While the company has done exactly that in more than one hundred languages, Google’s quest continues as it seeks to add millions of library books, television broadcasts, and more to its searchable database. <br>Readers will learn about the amazing business acumen and computer wizardry that started the company on its astonishing course; the secret network of computers delivering lightning-fast search results; the unorthodox approach that has enabled it to challenge Microsoft’s dominance and shake up Wall Street. Even as it rides high, Google wrestles with difficult choices that will enable it to continue expanding while sustaining the guiding vision of its founders’ mantra: DO NO EVIL.\\\"<br><br><br><i>From the Hardcover edition.</i>\"," +
                "\"thumbnail\":\"http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=1&imgtk=AFLRE70pwzDs7di2l10gDISoQ9eAYlDDUJS1_RaIoansAZS2oPOHM0lgxj2OLvAb1NL0SVyx1pSOFLfv42uuMYlhZ-obp-C4UmDKrFfSFa-0s-WQ-7qSDdxOPwZOueRQcl-W6enWgsKr&source=gbs_api\"," +
                "\"smallThumbnail\":\"http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=5&imgtk=AFLRE72jG1eJOZ2lnI3TNwKGK8uSV3h7Igy_0ObYNp5SbXjTlnYmPGxPA9joI2RSyx8AYuk56AmWnbA6NAAI4PxkdUNe6-5iLtoHUBp2abMknLzLezbbjCR8nXw2qaLb0qRPgVs2z9eC&source=gbs_api\"," +
                "\"publisher\":\"Random House Publishing Group\"," +
                "\"publishedDate\":\"2005-11-15\"," +
                "\"price\":50," +
                "\"noOfCopies\":10," +
                "\"averageRating\":3.5," +
                "\"ratingCount\":20}]";
        when(bookService.getBooks()).thenReturn(books);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Book").accept(MediaType.APPLICATION_JSON);

        //Act
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Assert
        JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void getBookById() throws Exception {
        //Arrange
        Book book = new Book("zyTCAlFPjgYC",
                "The Google Story",
                Set.of(new Author("David A. Vise"),new Author("Mark Malseed")),
                Set.of(new Category("Business & Economics / Entrepreneurship"), new Category("Computers / Information Technology"), new Category("History / Modern / 20th Century")),
                "\"Here is the story behind one of the most remarkable Internet successes of our time. Based on scrupulous research and extraordinary access to Google, the book takes you inside the creation and growth of a company whose name is a favorite brand and a standard verb recognized around the world. Its stock is worth more than General Motors’ and Ford’s combined, its staff eats for free in a dining room that used to be\u003cb\u003e \u003c/b\u003erun\u003cb\u003e \u003c/b\u003eby the Grateful Dead’s former chef, and its employees traverse the firm’s colorful Silicon Valley campus on scooters and inline skates.\u003cbr\u003e\u003cbr\u003e\u003cb\u003eTHE GOOGLE STORY \u003c/b\u003eis the definitive account of the populist media company powered by the world’s most advanced technology that in a few short years has revolutionized access to information about everything for everybody everywhere. \u003cbr\u003eIn 1998, Moscow-born Sergey Brin and Midwest-born Larry Page dropped out of graduate school at Stanford University to, in their own words, “change the world” through a search engine that would organize every bit of information on the Web for free.\u003cbr\u003e\u003cbr\u003eWhile the company has done exactly that in more than one hundred languages, Google’s quest continues as it seeks to add millions of library books, television broadcasts, and more to its searchable database. \u003cbr\u003eReaders will learn about the amazing business acumen and computer wizardry that started the company on its astonishing course; the secret network of computers delivering lightning-fast search results; the unorthodox approach that has enabled it to challenge Microsoft’s dominance and shake up Wall Street. Even as it rides high, Google wrestles with difficult choices that will enable it to continue expanding while sustaining the guiding vision of its founders’ mantra: DO NO EVIL.\"\u003cbr\u003e\u003cbr\u003e\u003cbr\u003e\u003ci\u003eFrom the Hardcover edition.\u003c/i\u003e",
                "http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=1&imgtk=AFLRE70pwzDs7di2l10gDISoQ9eAYlDDUJS1_RaIoansAZS2oPOHM0lgxj2OLvAb1NL0SVyx1pSOFLfv42uuMYlhZ-obp-C4UmDKrFfSFa-0s-WQ-7qSDdxOPwZOueRQcl-W6enWgsKr&source=gbs_api",
                "http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=5&imgtk=AFLRE72jG1eJOZ2lnI3TNwKGK8uSV3h7Igy_0ObYNp5SbXjTlnYmPGxPA9joI2RSyx8AYuk56AmWnbA6NAAI4PxkdUNe6-5iLtoHUBp2abMknLzLezbbjCR8nXw2qaLb0qRPgVs2z9eC&source=gbs_api",
                "Random House Publishing Group",
                "2005-11-15",
                50,
                10,
                3.5,
                20);

        String expectedJson = "{\"id\":\"zyTCAlFPjgYC\"," +
                "\"title\":\"The Google Story\"," +
//                "\"authors\":[{\"id\":0,\"name\":\"David A. Vise\",\"books\":null},{\"id\":0,\"name\":\"Mark Malseed\",\"books\":null}]," +
//                "\"categories\":[{\"id\":0,\"name\":\"Business & Economics / Entrepreneurship\",\"books\":null},{\"id\":0,\"name\":\"Computers / Information Technology\",\"books\":null},{\"id\":0,\"name\":\"History / Modern / 20th Century\",\"books\":null}]," +
                "\"description\":\"\\\"Here is the story behind one of the most remarkable Internet successes of our time. Based on scrupulous research and extraordinary access to Google, the book takes you inside the creation and growth of a company whose name is a favorite brand and a standard verb recognized around the world. Its stock is worth more than General Motors’ and Ford’s combined, its staff eats for free in a dining room that used to be<b> </b>run<b> </b>by the Grateful Dead’s former chef, and its employees traverse the firm’s colorful Silicon Valley campus on scooters and inline skates.<br><br><b>THE GOOGLE STORY </b>is the definitive account of the populist media company powered by the world’s most advanced technology that in a few short years has revolutionized access to information about everything for everybody everywhere. <br>In 1998, Moscow-born Sergey Brin and Midwest-born Larry Page dropped out of graduate school at Stanford University to, in their own words, “change the world” through a search engine that would organize every bit of information on the Web for free.<br><br>While the company has done exactly that in more than one hundred languages, Google’s quest continues as it seeks to add millions of library books, television broadcasts, and more to its searchable database. <br>Readers will learn about the amazing business acumen and computer wizardry that started the company on its astonishing course; the secret network of computers delivering lightning-fast search results; the unorthodox approach that has enabled it to challenge Microsoft’s dominance and shake up Wall Street. Even as it rides high, Google wrestles with difficult choices that will enable it to continue expanding while sustaining the guiding vision of its founders’ mantra: DO NO EVIL.\\\"<br><br><br><i>From the Hardcover edition.</i>\"," +
                "\"thumbnail\":\"http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=1&imgtk=AFLRE70pwzDs7di2l10gDISoQ9eAYlDDUJS1_RaIoansAZS2oPOHM0lgxj2OLvAb1NL0SVyx1pSOFLfv42uuMYlhZ-obp-C4UmDKrFfSFa-0s-WQ-7qSDdxOPwZOueRQcl-W6enWgsKr&source=gbs_api\"," +
                "\"smallThumbnail\":\"http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=5&imgtk=AFLRE72jG1eJOZ2lnI3TNwKGK8uSV3h7Igy_0ObYNp5SbXjTlnYmPGxPA9joI2RSyx8AYuk56AmWnbA6NAAI4PxkdUNe6-5iLtoHUBp2abMknLzLezbbjCR8nXw2qaLb0qRPgVs2z9eC&source=gbs_api\"," +
                "\"publisher\":\"Random House Publishing Group\"," +
                "\"publishedDate\":\"2005-11-15\"," +
                "\"price\":50," +
                "\"noOfCopies\":10," +
                "\"averageRating\":3.5," +
                "\"ratingCount\":20}";
        when(bookService.getBookById("zyTCAlFPjgYC")).thenReturn(book);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Book/zyTCAlFPjgYC").accept(MediaType.APPLICATION_JSON);

        //Act
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Assert
        JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void ShouldReturn200HttpStatusCodeWhenPayloadIsValidAndNewBookIsAdded() throws Exception {
        //Arrange
        String book = "{\"id\":\"zyTCAlFPjgYC\",\"title\":\"The Google Story\",\"authors\":[\"David A. Vise\",\"Mark Malseed\"]," +
                "\"categories\":[\"Business & Economics / Entrepreneurship\",\"Computers / Information Technology\",\"History / Modern / 20th Century\"]," +
                "\"description\":\"\\\"Here is the story behind one of the most remarkable Internet successes of our time. Based on scrupulous research and extraordinary access to Google, the book takes you inside the creation and growth of a company whose name is a favorite brand and a standard verb recognized around the world. Its stock is worth more than General Motors’ and Ford’s combined, its staff eats for free in a dining room that used to be<b> </b>run<b> </b>by the Grateful Dead’s former chef, and its employees traverse the firm’s colorful Silicon Valley campus on scooters and inline skates.<br><br><b>THE GOOGLE STORY </b>is the definitive account of the populist media company powered by the world’s most advanced technology that in a few short years has revolutionized access to information about everything for everybody everywhere. <br>In 1998, Moscow-born Sergey Brin and Midwest-born Larry Page dropped out of graduate school at Stanford University to, in their own words, “change the world” through a search engine that would organize every bit of information on the Web for free.<br><br>While the company has done exactly that in more than one hundred languages, Google’s quest continues as it seeks to add millions of library books, television broadcasts, and more to its searchable database. <br>Readers will learn about the amazing business acumen and computer wizardry that started the company on its astonishing course; the secret network of computers delivering lightning-fast search results; the unorthodox approach that has enabled it to challenge Microsoft’s dominance and shake up Wall Street. Even as it rides high, Google wrestles with difficult choices that will enable it to continue expanding while sustaining the guiding vision of its founders’ mantra: DO NO EVIL.\\\"<br><br><br><i>From the Hardcover edition.</i>\"," +
                "\"thumbnail\":\"http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=1&imgtk=AFLRE70pwzDs7di2l10gDISoQ9eAYlDDUJS1_RaIoansAZS2oPOHM0lgxj2OLvAb1NL0SVyx1pSOFLfv42uuMYlhZ-obp-C4UmDKrFfSFa-0s-WQ-7qSDdxOPwZOueRQcl-W6enWgsKr&source=gbs_api\"," +
                "\"smallThumbnail\":\"http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=5&imgtk=AFLRE72jG1eJOZ2lnI3TNwKGK8uSV3h7Igy_0ObYNp5SbXjTlnYmPGxPA9joI2RSyx8AYuk56AmWnbA6NAAI4PxkdUNe6-5iLtoHUBp2abMknLzLezbbjCR8nXw2qaLb0qRPgVs2z9eC&source=gbs_api\"," +
                "\"publisher\":\"Random House Publishing Group\",\"publishedDate\":\"2005-11-15\",\"price\":50,\"noOfCopies\":10,\"averageRating\":3.5,\"ratingCount\":20}";
        when(bookService.add(Mockito.any(Book.class))).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/Book").accept(MediaType.APPLICATION_JSON).content(book)
                .contentType(MediaType.APPLICATION_JSON);

        //Act
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Assert
        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
    }

    @Test
    public void ShouldReturn400HttpStatusCodeWhenPayloadIsNotValid() throws Exception {
        //Arrange
        String book = "{}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/Book").contentType(MediaType.APPLICATION_JSON).content(book)
                .contentType(MediaType.APPLICATION_JSON);
        when(bookService.add(Mockito.any(Book.class))).thenReturn(true);

        //Act
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }

    @Test
    public void shouldReturn200HttpStatusCodeWhenABookIsDeleted() throws Exception {
        //Arrange
        when(bookService.delete("zyTCAlFPjgYC")).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/Book/zyTCAlFPjgYC").accept(MediaType.APPLICATION_JSON);

        //Act
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Assert
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void shouldReturn404HttpStatusCodeWhenBookToBeDeletedIsNotPresent() throws Exception {
        //Arrange
        when(bookService.delete("zyTCAlFPjgYg")).thenReturn(false);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/Book/zyTCAlFPjgYg").accept(MediaType.APPLICATION_JSON);

        //Act
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Assert
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }

    @Test
    public void shouldReturnAllCopiesOfABook() throws Exception {
        //Arrange
        String expectedJson = "[{\"id\":\"id:1\"," +
                "\"status\":\"Available\"" +
                "},{\"id\":\"id:2\"," +
                "\"status\":\"Rented\"}]";
        List<Copy> copies = Arrays.asList(new Copy("id:1", CopyStatus.Available, new Book()), new Copy("id:2", CopyStatus.Rented, new Book()));
        when(bookService.getCopiesByBookId(Mockito.anyString())).thenReturn(copies);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Book/zyTCAlFPjgYg/Copy").accept(MediaType.APPLICATION_JSON);

        //Act
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Assert
        JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void shouldReturnParticularCopyOfTheBook() throws Exception {
        //Arrange
        String expectedJson = "{\"id\":\"id:1\"," +
                "\"status\":\"Available\"}";
        Copy copy = new Copy("id:1", CopyStatus.Available, new Book());
        when(bookService.getCopyById("id:1")).thenReturn(copy);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Copy/id:1").accept(MediaType.APPLICATION_JSON);

        //Act
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Assert
        assertEquals(expectedJson, result.getResponse().getContentAsString());
    }
}