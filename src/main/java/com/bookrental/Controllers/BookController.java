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
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;

    //Book Apis
    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable String id){
        return bookService.getBookById(id);
    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book){
        if(book.isValid())
        {
            if(bookService.add(book))
                return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable String id){
        if(bookService.delete(id)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}/order")
    public Copy orderBook(@PathVariable String id){
        return bookService.orderBook(id);
    }

    //Copy Apis
    @GetMapping("/{id}/copies")
    public List<Copy> getCopies(@PathVariable String id){
        return bookService.getCopiesByBookId(id);
    }

    @GetMapping("/copies/{id}")
    public Copy getCopy(@PathVariable String id){
        return bookService.getCopyById(id);
    }

    @PutMapping("/copies/{id}/return")
    public ResponseEntity<String> returnBook(@PathVariable String id){
        if(bookService.returnBook(id))
            return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
