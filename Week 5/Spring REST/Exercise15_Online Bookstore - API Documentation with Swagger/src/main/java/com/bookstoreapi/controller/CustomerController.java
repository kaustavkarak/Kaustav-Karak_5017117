package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.dto.CustomerDTO;
import com.example.onlinebookstore.model.Customer;
import com.example.onlinebookstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getEmail(), customerDTO.getPassword());
        Customer savedCustomer = customerService.save(customer);
        return new ResponseEntity<>(new CustomerDTO(savedCustomer.getId(), savedCustomer.getName(), savedCustomer.getEmail(), savedCustomer.getPassword()), HttpStatus.CREATED);
    }
}
