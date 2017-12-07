package com.bookrental.Services;

import com.bookrental.Models.Author;
import com.bookrental.Models.Book;
import com.bookrental.Models.Category;
import com.bookrental.Repositories.AuthorRepository;
import com.bookrental.Repositories.BookRepository;
import com.bookrental.Repositories.CategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    @Autowired
    BookService bookService;

    @Test
    public void shouldReturnListOfBooks() {
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
        when(bookRepository.findAll()).thenReturn(books);

        //Act and Assert
        Assert.assertEquals(books, bookService.getBooks());
    }

    @Test
    public void shouldReturnBookById() {
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
        when(bookRepository.findOne("zyTCAlFPjgYC")).thenReturn(book);

        //Act and Assert
        Assert.assertEquals(book, bookService.getBookById("zyTCAlFPjgYC"));
    }

    @Test
    public void add() {
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
        when(bookRepository.findOne("zyTCAlFPjgYC")).thenReturn(book);

        //Act and Assert
        Assert.assertEquals(true, bookService.delete("zyTCAlFPjgYC"));
    }
}
