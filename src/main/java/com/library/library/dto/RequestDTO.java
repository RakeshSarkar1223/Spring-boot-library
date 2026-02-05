package com.library.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RequestDTO {
    @NotBlank(message = "Title cannot empty!")
    @Size(max = 100, min = 2, message = "Title should be in 2 to 100 characters")
    private String title;

    @NotBlank(message = "Author cannot empty!")
    @Size(max = 100, min = 2, message = "Author should be in 2 to 100 characters")
    private String author;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
