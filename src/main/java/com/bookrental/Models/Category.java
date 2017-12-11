package com.bookrental.Models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

}
