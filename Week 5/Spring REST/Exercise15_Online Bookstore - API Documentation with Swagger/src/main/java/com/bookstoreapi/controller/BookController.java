package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.dto.BookDTO;
import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        List<Book> books = bookService.findAll();

        if (title != null) {
            books = books.stream().filter(book -> book.getTitle().equalsIgnoreCase(title)).collect(Collectors.toList());
        }

        if (author != null) {
            books = books.stream().filter(book -> book.getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList());
        }

        List<BookDTO> bookDTOs = books.stream().map(book -> new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getIsbn())).collect(Collectors.toList());
        return new ResponseEntity<>(bookDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        return book.map(b -> new ResponseEntity<>(new BookDTO(b.getId(), b.getTitle(), b.getAuthor(), b.getPrice(), b.getIsbn()), HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        Book book = new Book(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPrice(), bookDTO.getIsbn());
        Book savedBook = bookService.save(book);
        return new ResponseEntity<>(new BookDTO(savedBook.getId(), savedBook.getTitle(), savedBook.getAuthor(), savedBook.getPrice(), savedBook.getIsbn()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
