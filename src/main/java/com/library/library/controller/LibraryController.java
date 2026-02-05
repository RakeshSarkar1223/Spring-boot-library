package com.library.library.controller;

import com.library.library.LibraryApplication;
import com.library.library.dto.RequestDTO;
import com.library.library.dto.ResponseDTO;
import com.library.library.model.Book;
import com.library.library.service.LibraryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class LibraryController {
    private final LibraryService libraryService;
    public LibraryController(LibraryService libraryService){
        this.libraryService = libraryService;
    }

    @GetMapping
    public Page<ResponseDTO> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id")String sortBy
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return libraryService.findAll(pageable)
                .map(book -> new ResponseDTO(book.getId(),book.getTitle(),book.getAuthor()));
    }

    @PostMapping
    public ResponseDTO addBook(@Valid @RequestBody RequestDTO book){
        Book savedBook = libraryService.addBook(book);
        return new ResponseDTO(savedBook.getId(), savedBook.getTitle(), savedBook.getAuthor());
    }

    @PutMapping("/{id}")
    public ResponseDTO updateBook(@PathVariable int id,@Valid @RequestBody RequestDTO book){
        Book updatedBook = libraryService.updateBook(id, book);
        return new ResponseDTO(updatedBook.getId(),updatedBook.getTitle(),updatedBook.getAuthor());
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id){
        return libraryService.deleteBook(id);
    }
}
