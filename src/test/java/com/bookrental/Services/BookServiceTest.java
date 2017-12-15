package com.bookrental.Services;

import com.bookrental.BookBuilder.BookBuilder;
import com.bookrental.Models.*;
import com.bookrental.Repositories.AuthorRepository;
import com.bookrental.Repositories.BookRepository;
import com.bookrental.Repositories.CategoryRepository;
import com.bookrental.Repositories.CopyRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BookService.class, secure = false)
public class BookServiceTest {

    @MockBean
    BookRepository bookRepository;
    @MockBean
    AuthorRepository authorRepository;
    @MockBean
    CategoryRepository categoryRepository;
    @MockBean
    CopyRepository copyRepository;

    @Autowired
    BookService bookService;

    @Test
    public void shouldReturnListOfBooks() {
        //Arrange
        List<Book> books = Arrays.asList(new BookBuilder().buildBook());
        when(bookRepository.findAll()).thenReturn(books);

        //Act and Assert
        Assert.assertEquals(books, bookService.getBooks());
    }

    @Test
    public void shouldReturnBookById() {
        //Arrange
        Book book = new BookBuilder().buildBook();
        when(bookRepository.findOne("zyTCAlFPjgYC")).thenReturn(book);

        //Act and Assert
        Assert.assertEquals(book, bookService.getBookById("zyTCAlFPjgYC"));
    }

    @Test
    public void shouldReturnFalseWhenBookToBeDeletedNotPresent() {
        //Arrange
        when(bookRepository.findOne("zyTCAlFPjgYC")).thenReturn(null);

        //Act and Assert
        Assert.assertEquals(false, bookService.delete("zyTCAlFPjgYC"));
    }

    @Test
    public void shouldReturnTrueWhenBookIsPresentInDatabaseAndDeletedSuccessfully(){
        //Arrange
        Book book = new BookBuilder().buildBook();
        when(bookRepository.findOne("zyTCAlFPjgYC")).thenReturn(book);

        //Act and Assert
        Assert.assertEquals(true, bookService.delete("zyTCAlFPjgYC"));
    }

    @Test
    public void shouldReturnAllTheCopiesOfABook() {
        //Arrange
        Book book = new BookBuilder().setNoOfCopies(2).buildBook();
        book.setCopy();
        List<Copy> copies = Arrays.asList(new Copy("id:1", CopyStatus.Available, new Book()), new Copy("id:2", CopyStatus.Rented, new Book()));
        when(bookRepository.findOne("zyTCAlFPjgYC")).thenReturn(book);

        //Act and Assert
        System.out.println(bookService.getCopiesByBookId("zyTCAlFPjgYC"));
        Assert.assertEquals(copies.size(), bookService.getCopiesByBookId("zyTCAlFPjgYC").size());
    }

    @Test
    public void shouldReturnParticularCopyOfABook(){
        //Arrange
        Copy copy = new Copy("id:1", CopyStatus.Available, new Book());
        when(copyRepository.findOne(Mockito.anyString())).thenReturn(copy);

        //Act and Assert
        Assert.assertEquals(copy, bookService.getCopyById("id:1"));
    }

    @Test
    public void shouldRentFirstCopyOfTheMovieWhichIsAvailable(){
        //Arrange
        Book book = new BookBuilder().buildBook();
        when(bookRepository.findOne(Mockito.anyString())).thenReturn(book);

        //Act and Assert
        Assert.assertEquals("zyTCAlFPjgYC:1", bookService.orderBook("zyTCAlFPjgYC").getId());
    }

    @Test
    public void shouldReturnFalseWhenCopyOfBookToBeReturnedIsNotPresent(){
        //Arrange
        when(copyRepository.findOne(Mockito.anyString())).thenReturn(null);

        //Act and Assert
        Assert.assertEquals(false, bookService.returnBook("zyTCAlFPjgYC:1"));
    }

    @Test
    public void shouldReturnTrueWhenCopyOfBookIsSuccessfullyReturned(){
        //Arrange
        Book book = new BookBuilder().buildBook();
        when(copyRepository.findOne(Mockito.anyString())).thenReturn(new Copy("zyTCAlFPjgYC:1",CopyStatus.Rented, book));

        //Act and Assert
        Assert.assertEquals(true, bookService.returnBook("zyTCAlFPjgYC:1"));
    }
}
