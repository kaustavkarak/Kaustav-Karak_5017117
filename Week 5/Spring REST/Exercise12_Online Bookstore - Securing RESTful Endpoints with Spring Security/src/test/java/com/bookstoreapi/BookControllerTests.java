package com.example.bookstore;

import com.example.bookstore.controller.BookController;
import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookControllerTests {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        List<BookDTO> books = Arrays.asList(new BookDTO(1L, "Book 1", "Author 1", 100),
                new BookDTO(2L, "Book 2", "Author 2", 150));
        when(bookService.getAllBooks()).thenReturn(books);

        ResponseEntity<List<BookDTO>> response = bookController.getAllBooks();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void testGetBookById() {
        BookDTO book = new BookDTO(1L, "Book 1", "Author 1", 100);
        when(bookService.getBookById(1L)).thenReturn(book);

        ResponseEntity<BookDTO> response = bookController.getBookById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Book 1", response.getBody().getTitle());
        verify(bookService, times(1)).getBookById(1L);
    }

    @Test
    void testAddBook() {
        BookDTO book = new BookDTO(1L, "Book 1", "Author 1", 100);
        when(bookService.saveBook(book)).thenReturn(book);

        ResponseEntity<BookDTO> response = bookController.addBook(book);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Book 1", response.getBody().getTitle());
        verify(bookService, times(1)).saveBook(book);
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookService).deleteBook(1L);

        ResponseEntity<Void> response = bookController.deleteBook(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(bookService, times(1)).deleteBook(1L);
    }
}
