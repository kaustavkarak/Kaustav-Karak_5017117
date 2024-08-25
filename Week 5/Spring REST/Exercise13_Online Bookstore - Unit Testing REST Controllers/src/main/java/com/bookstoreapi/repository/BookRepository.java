package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);

    List<Book> findByIsbn(String isbn);

    List<Book> findByAuthor_Id(Long authorId);

    List<Book> findByPriceBetween(Double minPrice, Double maxPrice);
}
