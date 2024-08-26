package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Custom query method to find books by title
    List<Book> findByTitle(String title);

    // Custom query method to find books by author
    List<Book> findByAuthor(String author);

    // Custom query method to find books by title and author
    List<Book> findByTitleAndAuthor(String title, String author);
}
