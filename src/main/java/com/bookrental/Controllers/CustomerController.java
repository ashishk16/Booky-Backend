package com.bookrental.Controllers;

import com.bookrental.Models.Copy;
import com.bookrental.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    BookService bookService;

    @RequestMapping(method = RequestMethod.PUT, value = "/Book/{id}/Order")
    public Copy orderBook(@PathVariable String id){
        return bookService.orderBook(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/Book/Copy/{id}/Return")
    public ResponseEntity<String> returnBook(@PathVariable String id){
        if(bookService.returnBook(id))
            return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
