package com.bookrental.Services;

import com.bookrental.Exceptions.ResourceNotFoundException;
import com.bookrental.Models.Book;
import com.bookrental.Models.Copy;
import com.bookrental.Models.CopyStatus;
import com.bookrental.Repositories.AuthorRepository;
import com.bookrental.Repositories.BookRepository;
import com.bookrental.Repositories.CategoryRepository;
import com.bookrental.Repositories.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CopyRepository copyRepository;

    public BookService() {
    }

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }

    public Book getBookById(String id) {
        Book book = bookRepository.findOne(id);
        if(book == null )
            throw new ResourceNotFoundException(id, "book not found");
        return book;
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

    public List<Copy> getCopiesByBookId(String id) {
        Book book = bookRepository.findOne(id);
        return book.getCopies();
    }

    public Copy getCopyById(String id) {
        return copyRepository.findOne(id);
    }

    public Copy orderBook(String id) {
        Copy copy = bookRepository.findOne(id).getCopies().stream().filter(c -> c.getStatus().equals(CopyStatus.Available)).findFirst().get();
        copy.setStatus(CopyStatus.Rented);
        copyRepository.save(copy);
        return copy;
    }

    public boolean returnBook(String id) {
        Copy copy = copyRepository.findOne(id);
        if(copy == null)
            return false;
        copy.setStatus(CopyStatus.Available);
        copyRepository.save(copy);
        return true;
    }
}
