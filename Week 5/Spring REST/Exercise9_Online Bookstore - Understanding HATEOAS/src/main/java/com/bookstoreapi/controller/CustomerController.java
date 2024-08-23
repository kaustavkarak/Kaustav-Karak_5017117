package com.example.bookstore.controller;

import com.example.bookstore.dto.CustomerDTO;
import com.example.bookstore.model.Customer;
import com.example.bookstore.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<EntityModel<CustomerDTO>> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer createdCustomer = customerService.createCustomer(customer);
        CustomerDTO createdCustomerDTO = modelMapper.map(createdCustomer, CustomerDTO.class);

        EntityModel<CustomerDTO> resource = EntityModel.of(createdCustomerDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)
                .getCustomerById(createdCustomerDTO.getId())).withSelfRel());

        return ResponseEntity.created(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(createdCustomerDTO.getId())).toUri()).body(resource);
    }

    @GetMapping("/{id}")
    public EntityModel<CustomerDTO> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);

        EntityModel<CustomerDTO> resource = EntityModel.of(customerDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)
                .getCustomerById(id)).withSelfRel());
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)
                .getAllCustomers()).withRel("customers"));

        return resource;
    }

    @GetMapping
    public List<EntityModel<CustomerDTO>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return customers.stream()
                .map(customer -> {
                    CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
                    EntityModel<CustomerDTO> resource = EntityModel.of(customerDTO);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)
                            .getCustomerById(customerDTO.getId())).withSelfRel());
                    return resource;
                })
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public EntityModel<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        CustomerDTO updatedCustomerDTO = modelMapper.map(updatedCustomer, CustomerDTO.class);

        EntityModel<CustomerDTO> resource = EntityModel.of(updatedCustomerDTO);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)
                .getCustomerById(updatedCustomerDTO.getId())).withSelfRel());

        return resource;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
