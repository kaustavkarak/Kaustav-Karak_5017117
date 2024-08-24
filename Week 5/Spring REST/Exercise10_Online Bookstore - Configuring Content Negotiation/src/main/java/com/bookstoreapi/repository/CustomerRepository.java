package com.example.bookstore.repository;

import com.example.bookstore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Find a customer by email
    Optional<Customer> findByEmail(String email);

    // Find customers by last name
    List<Customer> findByLastName(String lastName);

    // Find customers by first name containing a keyword (case insensitive)
    List<Customer> findByFirstNameContainingIgnoreCase(String keyword);

    // Check if a customer exists by email
    boolean existsByEmail(String email);
}
