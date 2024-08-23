package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<EntityModel<BookDTO>> createBook(@RequestBody BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        Book createdBook = bookService.createBook(book);
        BookDTO createdBookDTO = modelMapper.map(createdBook, BookDTO.class);

        EntityModel<BookDTO> resource = EntityModel.of(createdBookDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class)
                .getBookById(createdBookDTO.getId())).withSelfRel());

        return ResponseEntity.created(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(BookController.class).getBookById(createdBookDTO.getId())).toUri()).body(resource);
    }

    @GetMapping("/{id}")
    public EntityModel<BookDTO> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);

        EntityModel<BookDTO> resource = EntityModel.of(bookDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class)
                .getBookById(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class)
                .getAllBooks()).withRel("books"));

        return resource;
    }

    @GetMapping
    public List<EntityModel<BookDTO>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return books.stream()
                .map(book -> {
                    BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
                    EntityModel<BookDTO> resource = EntityModel.of(bookDTO);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class)
                            .getBookById(bookDTO.getId())).withSelfRel());
                    return resource;
                })
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public EntityModel<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        Book updatedBook = bookService.updateBook(id, book);
        BookDTO updatedBookDTO = modelMapper.map(updatedBook, BookDTO.class);

        EntityModel<BookDTO> resource = EntityModel.of(updatedBookDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class)
                .getBookById(updatedBookDTO.getId())).withSelfRel());

        return resource;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
