package com.example.bookstoreapi.repository;

import com.example.bookstoreapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastNameContaining(String lastName);
    List<Customer> findByEmail(String email);
}
