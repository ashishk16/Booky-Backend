package com.bookrental.Models;

import javax.persistence.*;

@Entity
@Table(name = "Copy")
public class  Copy {
    @Id
    @Column(name = "copyid")
    private String id;
    private CopyStatus status;
    @ManyToOne
    @JoinColumn(name = "id")
    private Book book;

    public Copy() {
    }

    public Copy(String id, CopyStatus status, Book book) {
        this.id = id;
        this.status = status;
        this.book = book;
    }

    public String getId() {
        return id;
    }

    public CopyStatus getStatus() {
        return status;
    }

}
