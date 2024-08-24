package com.example.bookstore;

import com.example.bookstore.controller.CustomerController;
import com.example.bookstore.dto.CustomerDTO;
import com.example.bookstore.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerControllerTests {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCustomers() {
        List<CustomerDTO> customers = Arrays.asList(new CustomerDTO(1L, "Customer 1", "customer1@example.com"),
                new CustomerDTO(2L, "Customer 2", "customer2@example.com"));
        when(customerService.getAllCustomers()).thenReturn(customers);

        ResponseEntity<List<CustomerDTO>> response = customerController.getAllCustomers();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(customerService, times(1)).getAllCustomers();
    }

    @Test
    void testGetCustomerById() {
        CustomerDTO customer = new CustomerDTO(1L, "Customer 1", "customer1@example.com");
        when(customerService.getCustomerById(1L)).thenReturn(customer);

        ResponseEntity<CustomerDTO> response = customerController.getCustomerById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Customer 1", response.getBody().getName());
        verify(customerService, times(1)).getCustomerById(1L);
    }

    @Test
    void testAddCustomer() {
        CustomerDTO customer = new CustomerDTO(1L, "Customer 1", "customer1@example.com");
        when(customerService.saveCustomer(customer)).thenReturn(customer);

        ResponseEntity<CustomerDTO> response = customerController.addCustomer(customer);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Customer 1", response.getBody().getName());
        verify(customerService, times(1)).saveCustomer(customer);
    }

    @Test
    void testDeleteCustomer() {
        doNothing().when(customerService).deleteCustomer(1L);

        ResponseEntity<Void> response = customerController.deleteCustomer(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(customerService, times(1)).deleteCustomer(1L);
    }
}
