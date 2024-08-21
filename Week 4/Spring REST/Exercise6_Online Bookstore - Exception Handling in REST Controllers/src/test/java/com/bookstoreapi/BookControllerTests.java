package com.example.bookstore;

import com.example.bookstore.controller.BookNotFoundException;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        // Any setup tasks before each test case can be done here
    }

    @Test
    public void testCreateBook() throws Exception {
        Book book = new Book("Test Book", "Test Author", 9.99);
        given(bookRepository.save(any(Book.class))).willReturn(book);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("Test Author"))
                .andExpect(jsonPath("$.price").value(9.99));
    }

    @Test
    public void testGetBookById() throws Exception {
        Book book = new Book("Test Book", "Test Author", 9.99);
        given(bookRepository.findById(1L)).willReturn(Optional.of(book));

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(header().string("Custom-Header", "BookDetails"))
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("Test Author"))
                .andExpect(jsonPath("$.price").value(9.99));
    }

    @Test
    public void testGetBookById_NotFound() throws Exception {
        given(bookRepository.findById(1L)).willReturn(Optional.empty());

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllBooks() throws Exception {
        Book book = new Book("Test Book", "Test Author", 9.99);
        given(bookRepository.findAll()).willReturn(Collections.singletonList(book));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(header().string("Custom-Header", "BookList"))
                .andExpect(jsonPath("$[0].title").value("Test Book"))
                .andExpect(jsonPath("$[0].author").value("Test Author"))
                .andExpect(jsonPath("$[0].price").value(9.99));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Mockito.doNothing().when(bookRepository).deleteById(1L);

        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNoContent());
    }
}
