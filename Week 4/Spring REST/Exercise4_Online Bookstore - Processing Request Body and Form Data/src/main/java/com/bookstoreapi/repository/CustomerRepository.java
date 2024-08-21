package com.example.bookstore.repository;

import com.example.bookstore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Custom query method to find a customer by email
    Optional<Customer> findByEmail(String email);

    // Custom query method to find a customer by name
    Optional<Customer> findByName(String name);
}
