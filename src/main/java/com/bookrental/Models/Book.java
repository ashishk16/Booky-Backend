package com.bookrental.Models;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    private String id;
    private String title;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "book_authors", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private Set<Author> authors;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "book_categories", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Set<Category> categories;
    private String description;
    private String thumbnail;
    @Column(name="smallthumbnail")
    private String smallThumbnail;
    private String publisher;
    @Column(name="publisheddate")
    private String publishedDate;
    @Column(name = "noofcopies")
    private int noOfCopies;
    @Column(name="averagerating")
    private double averageRating;
    @Column(name="ratingcount")
    private int ratingCount;
    private double price;


    @OneToMany(targetEntity = Copy.class, mappedBy="book",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Copy> copies;

    public Book() {
    }

    public Book(String id, String title, Set<Author> authors, Set<Category> categories, String description, String thumbnail,
                String smallThumbnail, String publisher, String publishedDate, double price, int copies,
                double averageRating, int ratingCount) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.categories = categories;
        this.description = description;
        this.thumbnail = thumbnail;
        this.smallThumbnail = smallThumbnail;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.price = price;
        this.noOfCopies = copies;
        this.averageRating = averageRating;
        this.ratingCount = ratingCount;
    }

    public List<Copy> getCopies() {
        return copies;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public double getPrice() {
        return price;
    }

    public int getNoOfCopies() {
        return noOfCopies;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setCopy() {
        List<Copy> newCopies = new ArrayList<>();
        for(int i = 0; i < noOfCopies; i++)
            newCopies.add(new Copy(id+":"+(i+1), CopyStatus.Available, this));
        copies = newCopies;
    }
}
