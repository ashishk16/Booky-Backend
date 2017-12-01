package com.bookrental.Services;

import com.bookrental.Models.Book;
import com.bookrental.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public BookService() {
    }

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    public Book getBookById(String id) {
        return bookRepository.findOne(id);
    }

    public boolean add(Book book) {
        book.setCopy();
        bookRepository.save(book);
        return true;
    }

    public boolean delete(String id) {
        bookRepository.delete(id);
        return true;
    }
}
