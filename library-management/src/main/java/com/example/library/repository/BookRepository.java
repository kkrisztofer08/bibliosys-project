package com.example.library.repository;

import com.example.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByBorrowedFalse();
    Optional<Book> findByTitleAndAuthor(String title, String author);
    Optional<Book> findByAuthorAndTitle(String author, String title);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.borrowed = false WHERE b.title = :title AND b.author = :author")
    void setBorrowedFalseByTitleAndAuthor(@Param("title") String title, @Param("author") String author);

}