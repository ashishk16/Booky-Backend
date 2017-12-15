package com.bookrental.Models;

import com.bookrental.BookBuilder.BookBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Set;


public class BookTest {
    private Book book;

    @Before
    public void setUp(){
        book = new BookBuilder().setNoOfCopies(10).buildBook();
    }

    @Test
    public void shouldReturnTrueWhenGivenBookIsAValidBook(){
        //Arrange, Act and Assert
        Assert.assertEquals(true, book.isValid());
    }

    @Test
    public void shouldReturnFalseWhenGivenBookIsNotAValidBook(){
        //Arrange
        Book book = new Book();

        //Act and Assert
        Assert.assertEquals(false, book.isValid());
    }
}