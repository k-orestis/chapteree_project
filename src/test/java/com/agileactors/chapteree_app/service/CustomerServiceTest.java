package com.agileactors.chapteree_app.service;

import com.agileactors.chapteree_app.model.Customer;
import com.agileactors.chapteree_app.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

public class CustomerServiceTest {


    public static final long CUSTOMER_ID = 0L;
    private CustomerService customerService;
    private CustomerRepository customerRepository = mock(CustomerRepository.class);

    private Customer customer;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService(customerRepository);
        customer = new Customer(10L, "John", "Alogoouras");
    }

    @Test
    void findAll() {
        List<Customer> customers = List.of(customer, customer);
        when(customerRepository.findAll()).thenReturn(customers);
        List<Customer> customerList = customerRepository.findAll();
        assertEquals(customers.get(0).getFirstName(), customerList.get(0).getFirstName());
        assertEquals(customers.get(0).getLastName(), customerList.get(0).getLastName());
    }

    @Test
    void get() {
        when(customerRepository.getOne(CUSTOMER_ID)).thenReturn(customer);
        Customer newCustomer = customerService.getOne(CUSTOMER_ID);
        assertEquals(customer.getFirstName(), newCustomer.getFirstName());
        assertEquals(customer.getLastName(), newCustomer.getLastName());
    }

    @Test
    void save() {
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer newCustomer = customerService.save(customer);
        assertEquals(customer.getFirstName(), newCustomer.getFirstName());
        assertEquals(customer.getLastName(), newCustomer.getLastName());
    }

    @Test
    void delete() {
        customerService.deleteById( CUSTOMER_ID);
        verify(customerRepository).deleteById(CUSTOMER_ID);
    }

    @Test
    void update() {
        Customer newCustomer = new Customer(10L, "John", "Alogoouras");
        when(customerRepository.getOne(CUSTOMER_ID)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(newCustomer);

        Customer updatedCustomer = customerService.update(CUSTOMER_ID, newCustomer);

        assertEquals(newCustomer.getFirstName(), updatedCustomer.getFirstName());
        assertEquals(newCustomer.getLastName(), updatedCustomer.getLastName());
    }

    @Test
    void myChapterees() {
        when(customerRepository.getOne(CUSTOMER_ID)).thenReturn(customer);
        when(customerRepository.myChapterees(CUSTOMER_ID)).thenReturn(new ArrayList<>());
        assertEquals(1,customerService.myChapterees(CUSTOMER_ID).size());
    }
}
