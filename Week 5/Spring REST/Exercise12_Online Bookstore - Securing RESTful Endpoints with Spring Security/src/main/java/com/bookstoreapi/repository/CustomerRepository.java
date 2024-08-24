package com.example.bookstore.repository;

import com.example.bookstore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Custom query to find a customer by email
    Optional<Customer> findByEmail(String email);

    // Custom query to find all customers by name
    List<Customer> findByNameContaining(String name);
}
