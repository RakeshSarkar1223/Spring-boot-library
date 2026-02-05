package com.library.library.dto;

public class ResponseDTO {
    private int id;
    private String title;
    private String author;

    public ResponseDTO(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
