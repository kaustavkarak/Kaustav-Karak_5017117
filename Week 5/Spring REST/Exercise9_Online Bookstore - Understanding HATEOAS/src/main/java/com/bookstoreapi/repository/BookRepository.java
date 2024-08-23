package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Custom query methods

    // Find books by title
    List<Book> findByTitle(String title);

    // Find books by author
    List<Book> findByAuthor(String author);

    // Find books by price range
    List<Book> findByPriceBetween(Double minPrice, Double maxPrice);
}
