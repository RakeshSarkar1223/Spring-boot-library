package com.library.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity()
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Title cannot empty")
    @Size(min = 2, max = 100, message = "Title should be between 2 to 100 character")
    private String title;

    @NotBlank(message = "author cannot empty")
    @Size(min = 2, max = 100, message = "Author should be between 2 to 100 character")
    private String author;

    public Book(){}
    public Book(String title, String author){
//        this.id = id;
        this.author = author;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
