package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Find books by title
    List<Book> findByTitle(String title);

    // Find books by author
    List<Book> findByAuthor(String author);

    // Find books by title containing a keyword (case insensitive)
    List<Book> findByTitleContainingIgnoreCase(String keyword);

    // Find books with price greater than a specified value
    List<Book> findByPriceGreaterThan(Double price);

    // Find books by category (assuming you have a category field)
    // List<Book> findByCategory(String category);
}
