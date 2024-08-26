package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book save(Book book);
    void delete(Long id);
}
