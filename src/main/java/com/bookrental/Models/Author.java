package com.bookrental.Models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    int id;

    @Column(unique = true)
    String name;

    @ManyToMany(mappedBy = "authors")
    Set<Book> books;

    public Author() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "Name: "+name;
    }
}
