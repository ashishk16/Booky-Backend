package com.bookrental.Controllers;

import com.bookrental.BookBuilder.BookBuilder;
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
@WebMvcTest(value = BookController.class, secure = false)
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Autowired
    BookController bookController;

    @Test
    public void ShouldReturn200HttpStatusCodeWhenPayloadIsValidAndNewBookIsAdded() throws Exception {
        //Arrange
        Book book = new BookBuilder().buildBook();
        when(bookService.add(Mockito.any(Book.class))).thenReturn(true);

        //Act and Assert
        assertEquals(HttpStatus.CREATED, bookController.addBook(book).getStatusCode());
    }

    @Test
    public void ShouldReturn400HttpStatusCodeWhenPayloadIsNotValid() throws Exception {
        //Arrange
        Book book = new Book();
        when(bookService.add(Mockito.any(Book.class))).thenReturn(true);

        //Act nad Assert
        assertEquals(HttpStatus.BAD_REQUEST, bookController.addBook(book).getStatusCode());
    }

    @Test
    public void shouldReturn200HttpStatusCodeWhenABookIsDeleted() throws Exception {
        //Arrange
        when(bookService.delete("zyTCAlFPjgYC")).thenReturn(true);

        //Act and Assert
        assertEquals(HttpStatus.OK, bookController.deleteBook("zyTCAlFPjgYC").getStatusCode());
    }

    @Test
    public void shouldReturn404HttpStatusCodeWhenBookToBeDeletedIsNotPresent() throws Exception {
        //Arrange
        when(bookService.delete("zyTCAlFPjgYg")).thenReturn(false);

        //Act and Assert
        assertEquals(HttpStatus.NOT_FOUND, bookController.deleteBook("zyTCAlFPjgYC").getStatusCode());
    }

    @Test
    public void shouldReturn200HttpStatusCodeWhenABookIsReturned() throws Exception {
        //Arrange
        when(bookService.returnBook("zyTCAlFPjgYC:1")).thenReturn(true);

        //Act and Assert
        assertEquals(HttpStatus.OK, bookController.returnBook("zyTCAlFPjgYC:1").getStatusCode());
    }

    @Test
    public void shouldReturn404HttpStatusCodeWhenBookToBeReturnedIsNotPresentOrNotRentedByAnyoneYet() throws Exception {
        //Arrange
        when(bookService.delete("zyTCAlFPjgYg:1")).thenReturn(false);

        //Act and Assert
        assertEquals(HttpStatus.NOT_FOUND, bookController.returnBook("zyTCAlFPjgYC:1").getStatusCode());
    }

}