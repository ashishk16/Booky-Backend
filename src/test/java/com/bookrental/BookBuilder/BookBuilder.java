package com.bookrental.BookBuilder;

import com.bookrental.Models.Author;
import com.bookrental.Models.Book;
import com.bookrental.Models.Category;
import com.bookrental.Models.Copy;

import java.util.List;
import java.util.Set;

public class BookBuilder {
    private String id;
    private String title;
    private Set<Author> authors;
    private Set<Category> categories;
    private String description;
    private String thumbnail;
    private String smallThumbnail;
    private String publisher;
    private String publishedDate;
    private int noOfCopies;
    private double averageRating;
    private int ratingCount;
    private double price;
    private List<Copy> copies;

    public BookBuilder() {
        this.id = "zyTCAlFPjgYC";
        this.title = "The Google Story";
        this.authors = Set.of(new Author("David A. Vise"),new Author("Mark Malseed"));
        this.categories = Set.of(new Category("Business & Economics / Entrepreneurship"), new Category("Computers / Information Technology"), new Category("History / Modern / 20th Century"));
        this.description = "\"Here is the story behind one of the most remarkable Internet successes of our time. Based on scrupulous research and extraordinary access to Google";
        this.thumbnail = "http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=1&imgtk=AFLRE70pwzDs7di2l10gDISoQ9eAYlDDUJS1_RaIoansAZS2oPOHM0lgxj2OLvAb1NL0SVyx1pSOFLfv42uuMYlhZ-obp-C4UmDKrFfSFa-0s-WQ-7qSDdxOPwZOueRQcl-W6enWgsKr&source=gbs_api";
        this.smallThumbnail = "http://books.google.com/books/content?id=zyTCAlFPjgYC&printsec=frontcover&img=1&zoom=5&imgtk=AFLRE72jG1eJOZ2lnI3TNwKGK8uSV3h7Igy_0ObYNp5SbXjTlnYmPGxPA9joI2RSyx8AYuk56AmWnbA6NAAI4PxkdUNe6-5iLtoHUBp2abMknLzLezbbjCR8nXw2qaLb0qRPgVs2z9eC&source=gbs_api";
        this.publisher = "Random House Publishing Group";
        this.publishedDate = "2005-11-15";
        this.noOfCopies = 10;
        this.averageRating = 3.5;
        this.ratingCount = 20;
        this.price = 50;
    }

    public BookBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public BookBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder setAuthors(Set<Author> authors) {
        this.authors = authors;
        return this;
    }

    public BookBuilder setCategories(Set<Category> categories) {
        this.categories = categories;
        return this;
    }

    public BookBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public BookBuilder setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public BookBuilder setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
        return this;
    }

    public BookBuilder setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public BookBuilder setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
        return this;
    }

    public BookBuilder setNoOfCopies(int noOfCopies) {
        this.noOfCopies = noOfCopies;
        return this;
    }

    public BookBuilder setAverageRating(double averageRating) {
        this.averageRating = averageRating;
        return this;
    }

    public BookBuilder setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
        return this;
    }

    public BookBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public BookBuilder setCopies(List<Copy> copies) {
        this.copies = copies;
        return this;
    }

    public Book buildBook(){
        Book book = new Book(id,title,authors,categories,description,thumbnail,smallThumbnail,publisher,publishedDate,price,noOfCopies,averageRating,ratingCount);
        book.setCopy();
        return book;
    }
}
