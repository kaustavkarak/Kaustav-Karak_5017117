package com.example.bookstore;

import com.example.bookstore.controller.CustomerController;
import com.example.bookstore.dto.CustomerDTO;
import com.example.bookstore.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerControllerTests {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCustomers() {
        // Arrange
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setId(1L);
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setEmail("john.doe@example.com");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setId(2L);
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setEmail("jane.doe@example.com");

        List<CustomerDTO> customers = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        // Act
        ResponseEntity<List<CustomerDTO>> response = customerController.getAllCustomers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers.size(), response.getBody().size());
    }

    @Test
    void testCreateCustomer() {
        // Arrange
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("New");
        customerDTO.setLastName("Customer");
        customerDTO.setEmail("new.customer@example.com");

        CustomerDTO createdCustomerDTO = new CustomerDTO();
        createdCustomerDTO.setId(1L);
        createdCustomerDTO.setFirstName("New");
        createdCustomerDTO.setLastName("Customer");
        createdCustomerDTO.setEmail("new.customer@example.com");

        when(customerService.createCustomer(customerDTO)).thenReturn(createdCustomerDTO);

        // Act
        ResponseEntity<CustomerDTO> response = customerController.createCustomer(customerDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdCustomerDTO.getId(), response.getBody().getId());
    }

    @Test
    void testUpdateCustomer() {
        // Arrange
        Long customerId = 1L;
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Updated");
        customerDTO.setLastName("Customer");
        customerDTO.setEmail("updated.customer@example.com");

        CustomerDTO updatedCustomerDTO = new CustomerDTO();
        updatedCustomerDTO.setId(customerId);
        updatedCustomerDTO.setFirstName("Updated");
        updatedCustomerDTO.setLastName("Customer");
        updatedCustomerDTO.setEmail("updated.customer@example.com");

        when(customerService.updateCustomer(customerId, customerDTO)).thenReturn(updatedCustomerDTO);

        // Act
        ResponseEntity<CustomerDTO> response = customerController.updateCustomer(customerId, customerDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCustomerDTO.getId(), response.getBody().getId());
    }

    @Test
    void testDeleteCustomer() {
        // Arrange
        Long customerId = 1L;
        doNothing().when(customerService).deleteCustomer(customerId);

        // Act
        ResponseEntity<Void> response = customerController.deleteCustomer(customerId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(customerService, times(1)).deleteCustomer(customerId);
    }
}
