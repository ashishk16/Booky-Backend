package com.bookrental.Models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(unique = true)
    String name;

    @ManyToMany(mappedBy = "categories")
    Set<Book> books;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, Set<Book> books) {
        this.name = name;
        this.books = books;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public String getName() {
        return name;
    }

}
