package com.example.bookstore.repository;

import com.example.bookstore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByName(String name);

    List<Customer> findByEmail(String email);

    List<Customer> findByPhone(String phone);

    List<Customer> findByAddressContaining(String address);
}
