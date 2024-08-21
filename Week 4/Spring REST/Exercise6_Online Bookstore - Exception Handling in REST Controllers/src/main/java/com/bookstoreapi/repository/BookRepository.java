package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByPriceLessThan(double price);

    List<Book> findByTitleContaining(String keyword);

    boolean existsByTitle(String title);

    void deleteByAuthor(String author);
}
