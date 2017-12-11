package com.bookrental.Controllers;
import com.bookrental.Models.Book;
import com.bookrental.Models.Copy;
import com.bookrental.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MerchantController {
    @Autowired
    BookService bookService;

    //Book Apis
    @RequestMapping("/Book")
    public List<Book> getAllBooks(){
        return bookService.getBooks();
    }

    @RequestMapping("/Book/{id}")
    public Book getBookById(@PathVariable String id){
        return bookService.getBookById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/Book")
    public ResponseEntity<String> addBook(@RequestBody Book book){
        if(ValidatePayload(book))
        {
            if(bookService.add(book))
                return ResponseEntity.status(HttpStatus.CREATED).build();
            else
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable String id){
        if(bookService.delete(id)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //Copy Apis
    @RequestMapping("/Book/{id}/Copy")
    public List<Copy> getCopies(@PathVariable String id){
        return bookService.getCopiesByBookId(id);
    }

    @RequestMapping("/Copy/{id}")
    public Copy getCopy(@PathVariable String id){
        return bookService.getCopyById(id);
    }

    private boolean ValidatePayload(Book book) {
        if( book.getId()!= null && book.getTitle()!=null && book.getAuthors()!=null && book.getCategories()!=null && book.getDescription()!=null
                && book.getThumbnail()!=null && book.getSmallThumbnail()!=null && book.getPublisher()!=null && book.getPublishedDate()!=null &&
                book.getPrice()!=0 && book.getNoOfCopies()!=0 && book.getAverageRating()!=0 && book.getRatingCount()!=0)
            return true;
        return false;
    }
}
