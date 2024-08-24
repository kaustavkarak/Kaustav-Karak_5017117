package com.example.bookstore;

import com.example.bookstore.controller.BookController;
import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookControllerTests {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        // Arrange
        BookDTO book1 = new BookDTO();
        book1.setId(1L);
        book1.setTitle("Effective Java");
        book1.setAuthor("Joshua Bloch");
        book1.setPrice(45.00);

        BookDTO book2 = new BookDTO();
        book2.setId(2L);
        book2.setTitle("Clean Code");
        book2.setAuthor("Robert C. Martin");
        book2.setPrice(50.00);

        List<BookDTO> books = Arrays.asList(book1, book2);

        when(bookService.getAllBooks()).thenReturn(books);

        // Act
        ResponseEntity<List<BookDTO>> response = bookController.getAllBooks();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(books.size(), response.getBody().size());
    }

    @Test
    void testCreateBook() {
        // Arrange
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("New Book");
        bookDTO.setAuthor("Author");
        bookDTO.setPrice(30.00);

        BookDTO createdBookDTO = new BookDTO();
        createdBookDTO.setId(1L);
        createdBookDTO.setTitle("New Book");
        createdBookDTO.setAuthor("Author");
        createdBookDTO.setPrice(30.00);

        when(bookService.createBook(bookDTO)).thenReturn(createdBookDTO);

        // Act
        ResponseEntity<BookDTO> response = bookController.createBook(bookDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdBookDTO.getId(), response.getBody().getId());
    }

    @Test
    void testUpdateBook() {
        // Arrange
        Long bookId = 1L;
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Updated Book");
        bookDTO.setAuthor("Updated Author");
        bookDTO.setPrice(35.00);

        BookDTO updatedBookDTO = new BookDTO();
        updatedBookDTO.setId(bookId);
        updatedBookDTO.setTitle("Updated Book");
        updatedBookDTO.setAuthor("Updated Author");
        updatedBookDTO.setPrice(35.00);

        when(bookService.updateBook(bookId, bookDTO)).thenReturn(updatedBookDTO);

        // Act
        ResponseEntity<BookDTO> response = bookController.updateBook(bookId, bookDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBookDTO.getId(), response.getBody().getId());
    }

    @Test
    void testDeleteBook() {
        // Arrange
        Long bookId = 1L;
        doNothing().when(bookService).deleteBook(bookId);

        // Act
        ResponseEntity<Void> response = bookController.deleteBook(bookId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(bookService, times(1)).deleteBook(bookId);
    }
}
