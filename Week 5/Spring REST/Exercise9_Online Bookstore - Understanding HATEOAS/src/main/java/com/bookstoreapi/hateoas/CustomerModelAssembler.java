package com.example.bookstore.hateoas;

import com.example.bookstore.controller.CustomerController;
import com.example.bookstore.model.Customer;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {

    @Override
    public EntityModel<Customer> toModel(Customer customer) {
        return EntityModel.of(customer,
                linkTo(methodOn(CustomerController.class).getCustomerById(customer.getId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("customers"));
    }

    @Override
    public CollectionModel<EntityModel<Customer>> toCollectionModel(Iterable<? extends Customer> entities) {
        CollectionModel<EntityModel<Customer>> customerModels = RepresentationModelAssembler.super.toCollectionModel(entities);
        customerModels.add(linkTo(methodOn(CustomerController.class).getAllCustomers()).withSelfRel());
        return customerModels;
    }
}
