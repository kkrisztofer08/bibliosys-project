package com.example.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String title;
    private String author;

    @NotBlank(message = "Az ISBN szám nem lehet üres")
    @Size(min = 10, max = 13, message = "Az ISBN 10 és 13 közötti karakter kell legyen")
    private String isbn;
    private Integer publicationYear;
    private boolean borrowed = false;

    public Book() {}

    public Book(String title, String author, int publicationYear, boolean borrowed) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.borrowed = borrowed;
    }

    // --- Getter & Setter ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}