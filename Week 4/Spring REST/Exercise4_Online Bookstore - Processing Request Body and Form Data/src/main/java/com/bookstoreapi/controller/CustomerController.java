package com.example.bookstore.controller;

import com.example.bookstore.model.Customer;
import com.example.bookstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/register/json")
    public ResponseEntity<Customer> registerCustomerJson(@RequestBody Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(savedCustomer);
    }

    @PostMapping("/register/form")
    public ModelAndView registerCustomerForm(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);

        customerRepository.save(customer);
        return new ModelAndView("registrationSuccess");
    }
}
