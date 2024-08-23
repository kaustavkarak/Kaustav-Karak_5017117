package com.example.bookstore.repository;

import com.example.bookstore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Custom query methods

    // Find a customer by email
    Optional<Customer> findByEmail(String email);

    // Find customers by name
    List<Customer> findByName(String name);
}
