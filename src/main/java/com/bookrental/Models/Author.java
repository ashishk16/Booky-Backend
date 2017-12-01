package com.bookrental.Models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="author")
public class Author {
    @Id
    @Column(unique = true)
    String name;

    @ManyToMany(mappedBy = "authors")
    Set<Book> books;

    public Author() {
    }

    public Author(String authorName, Set<Book> books) {
        this.name = authorName;
        this.books = books;
    }

    public Author(String name) {
        this.name = name;
    }

    public String getname() {
        return name;
    }

    public Set<Book> getBooks() {
        return books;
    }
}
