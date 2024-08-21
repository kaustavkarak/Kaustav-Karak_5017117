package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Find a book by its title
    Optional<Book> findByTitle(String title);

    // Find all books by a specific author
    List<Book> findByAuthor(String author);

    // Find books with a price less than a specified amount
    List<Book> findByPriceLessThan(double price);

    // Find books with a title containing a specified keyword
    List<Book> findByTitleContaining(String keyword);

    // Check if a book with a specific title exists
    boolean existsByTitle(String title);

    // Delete books by a specific author
    void deleteByAuthor(String author);
}
