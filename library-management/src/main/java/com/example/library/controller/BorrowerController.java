package com.example.library.controller;


import com.example.library.dto.BorrowerDTO;
import com.example.library.entity.Book;
import com.example.library.entity.Borrower;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {

    @Autowired
    private final BorrowerRepository borrowerRepository;
    private final BookRepository bookRepository;

    public BorrowerController(BorrowerRepository borrowerRepository, BookRepository bookRepository) {
        this.borrowerRepository = borrowerRepository;
        this.bookRepository = bookRepository;
    }

    @PostMapping
    public ResponseEntity<String> createBorrower(@RequestBody Borrower borrower) {
        borrowerRepository.save(borrower);

        // Állítsd a könyvet kölcsönzötté
        Book book = bookRepository.findByTitleAndAuthor(borrower.getBookTitle(), borrower.getBookAuthor())
                .orElse(null);
        if (book != null) {
            book.setBorrowed(true);
            bookRepository.save(book);
        }

        return ResponseEntity.ok("Kölcsönzés sikeresen rögzítve");
    }

    @DeleteMapping("/borrowers")
    public ResponseEntity<Void> returnBook(@RequestBody BorrowerDTO dto) {
        borrowerRepository.deleteByBookTitleAndBookAuthor(dto.getBookTitle(), dto.getBookAuthor());
        bookRepository.setBorrowedFalseByTitleAndAuthor(dto.getBookTitle(), dto.getBookAuthor());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Borrower> getAllBorrowers() {
        return borrowerRepository.findAll();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBorrower(@RequestBody Borrower borrower) {
        try {
            // Törlés a borrower táblából
            borrowerRepository.delete(borrower);

            // Könyv státuszának frissítése
            Book book = bookRepository.findByAuthorAndTitle(borrower.getBookAuthor(), borrower.getBookTitle())
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            if (book != null) {
                book.setBorrowed(false);
                bookRepository.save(book);
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hiba történt: " + e.getMessage());
        }
    }
}
