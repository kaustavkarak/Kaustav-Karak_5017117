package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void performService() {
        // Business logic here
        System.out.println("BookService is performing service...");
        bookRepository.fetchBooks();
    }
}
