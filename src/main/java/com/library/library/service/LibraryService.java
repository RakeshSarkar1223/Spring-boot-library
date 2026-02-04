package com.library.library.service;

import com.library.library.model.Book;
import com.library.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    private final BookRepository bookRepository;

    public LibraryService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
//        return "Book is saved with id " + book.getId() + " and title " + book.getTitle();
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book updateBook(int id, Book book){
        Book existingBook = bookRepository
                .findById(id)
                .map(oldBook -> {
                    oldBook.setAuthor(book.getAuthor());
                    oldBook.setTitle(book.getTitle());
                    return oldBook;
                })
                .orElseThrow();
        bookRepository.save(existingBook);
        return existingBook;
    }

    public String deleteBook(int id){
        bookRepository.deleteById(id);
        return "Book is deleted with id " + id;
    }
}
