package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Custom query to find a book by its ISBN
    Optional<Book> findByIsbn(String isbn);

    // Custom query to find all books by title
    List<Book> findByTitleContaining(String title);
}
