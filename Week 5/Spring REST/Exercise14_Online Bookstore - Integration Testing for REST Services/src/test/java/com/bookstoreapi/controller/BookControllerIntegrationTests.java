package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.model.Author;
import com.example.bookstore.repository.AuthorRepository;
import com.example.bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void setup() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        author = authorRepository.save(author);

        BookDTO book1 = new BookDTO();
        book1.setTitle("Book One");
        book1.setIsbn("1234567890123");
        book1.setPrice(19.99);
        book1.setAuthorId(author.getId());
        bookRepository.save(book1);

        BookDTO book2 = new BookDTO();
        book2.setTitle("Book Two");
        book2.setIsbn("1234567890124");
        book2.setPrice(29.99);
        book2.setAuthorId(author.getId());
        bookRepository.save(book2);
    }

    @Test
    void getAllBooks() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Book One")))
                .andExpect(jsonPath("$[1].title", is("Book Two")));
    }

    @Test
    void createBook() throws Exception {
        Author author = authorRepository.findAll().get(0);

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Book Three");
        bookDTO.setIsbn("1234567890125");
        bookDTO.setPrice(39.99);
        bookDTO.setAuthorId(author.getId());

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Book Three\", \"isbn\": \"1234567890125\", \"price\": 39.99, \"authorId\": " + author.getId() + "}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("Book Three")));
    }
}
