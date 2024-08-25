package com.example.bookstore.controller;

import com.example.bookstore.dto.CustomerDTO;
import com.example.bookstore.model.Customer;
import com.example.bookstore.repository.CustomerRepository;
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
public class CustomerControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setup() {
        customerRepository.deleteAll();
        Customer customer1 = new Customer();
        customer1.setName("Alice");
        customer1.setEmail("alice@example.com");
        customer1.setPhone("1234567890");
        customer1.setAddress("123 Main St");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setName("Bob");
        customer2.setEmail("bob@example.com");
        customer2.setPhone("0987654321");
        customer2.setAddress("456 Elm St");
        customerRepository.save(customer2);
    }

    @Test
    void getAllCustomers() throws Exception {
        mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Alice")))
                .andExpect(jsonPath("$[1].name", is("Bob")));
    }

    @Test
    void createCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Charlie");
        customerDTO.setEmail("charlie@example.com");
        customerDTO.setPhone("9876543210");
        customerDTO.setAddress("789 Maple St");

        mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Charlie\", \"email\": \"charlie@example.com\", \"phone\": \"9876543210\", \"address\": \"789 Maple St\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Charlie")));
    }
}
