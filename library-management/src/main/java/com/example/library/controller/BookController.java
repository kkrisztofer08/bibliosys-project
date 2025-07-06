package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
//@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;

    }

    @GetMapping("/available")
    public List<Book> getAvailableBooks() {
        return bookRepository.findByBorrowedFalse();
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book savedBook = bookService.createBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.deleteBook(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/borrow")
    public ResponseEntity<String> borrowBook(@PathVariable Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Book book = optionalBook.get();
        if (book.isBorrowed()) {
            return ResponseEntity.badRequest().body("A könyv már kölcsönözve van.");
        }

        book.setBorrowed(true);
        bookRepository.save(book);

        return ResponseEntity.ok("Könyv sikeresen kölcsönözve.");
    }
}
