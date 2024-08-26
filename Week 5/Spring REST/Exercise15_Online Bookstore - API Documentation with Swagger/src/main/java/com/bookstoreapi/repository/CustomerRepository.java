package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Custom query method to find a customer by email
    Customer findByEmail(String email);
}
