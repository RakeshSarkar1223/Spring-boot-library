package com.library.library.service;

import com.library.library.dto.RequestDTO;
import com.library.library.dto.ResponseDTO;
import com.library.library.exception.BookNotFoundException;
import com.library.library.model.Book;
import com.library.library.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    private final BookRepository bookRepository;

    public LibraryService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Book addBook(RequestDTO book){

        return bookRepository.save(new Book(book.getTitle(),book.getAuthor()));
//        return "Book is saved with id " + book.getId() + " and title " + book.getTitle();
    }

    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book updateBook(int id, RequestDTO book){
        Book existingBook = bookRepository
                .findById(id)
                .orElseThrow(() -> new BookNotFoundException(
                        "Book not found with id " + id));

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());

        return bookRepository.save(existingBook);
    }

    public String deleteBook(int id){
        if(!bookRepository.existsById(id)){
            throw new BookNotFoundException("Book not found with id " + id);
        }
        bookRepository.deleteById(id);
        return "Book is deleted with id " + id;
    }
}
