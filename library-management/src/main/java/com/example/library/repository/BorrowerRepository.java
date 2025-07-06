package com.example.library.repository;

import com.example.library.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Borrower b WHERE b.bookTitle = :title AND b.bookAuthor = :author")
    void deleteByBookTitleAndBookAuthor(@Param("title") String title, @Param("author") String author);

}