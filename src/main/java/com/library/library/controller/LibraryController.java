package com.library.library.controller;

import com.library.library.LibraryApplication;
import com.library.library.model.Book;
import com.library.library.service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class LibraryController {
    private final LibraryService libraryService;
    public LibraryController(LibraryService libraryService){
        this.libraryService = libraryService;
    }

    @GetMapping
    public List<Book> getBooks(){
        return libraryService.findAll();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return libraryService.addBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book book){
        return libraryService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id){
        return libraryService.deleteBook(id);
    }
}
