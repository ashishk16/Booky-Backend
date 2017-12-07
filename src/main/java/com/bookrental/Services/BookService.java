package com.bookrental.Services;

import com.bookrental.Models.Author;
import com.bookrental.Models.Book;
import com.bookrental.Repositories.AuthorRepository;
import com.bookrental.Repositories.BookRepository;
import com.bookrental.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    CategoryRepository categoryRepository;

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

    public Boolean add(Book book) {
        book.setCopy();
        book.getAuthors().forEach(author -> {
            if(authorRepository.findByName(author.getname()) != null)
                author.setId(authorRepository.findByName(author.getname()).getId());
        });
        book.getCategories().forEach(category -> {
            if(categoryRepository.findByName(category.getName()) != null)
                category.setId(categoryRepository.findByName(category.getName()).getId());
        });
        if(bookRepository.save(book)!=null)
            return true;
        return false;
    }

    public boolean delete(String id) {
        if(bookRepository.findOne(id)!=null) {
            bookRepository.delete(id);
            return true;
        }
        return false;
    }
}
