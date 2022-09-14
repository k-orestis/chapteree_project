package com.agileactors.chapteree_app.repository;

import com.agileactors.chapteree_app.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-flyway_test.properties")
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void findAll() {
        List<Customer> customers = customerRepository.findAll();
        assertEquals(3, customers.size() , "We should have 3 customers!");
    }

    @Test
    @Transactional
    void getOne() {
        Customer customer = customerRepository.getOne(2L);
        assertEquals("Menelaos", customer.getFirstName());
        assertEquals("Nikitakis", customer.getLastName());
    }

    @Test
    void save() {
        Customer customer = new Customer(4L, "Stavros", "Kosmapetris");
        Customer newCustomer = customerRepository.save(customer);
        assertEquals("Stavros", newCustomer.getFirstName());
        assertEquals("Kosmapetris", newCustomer.getLastName());
        customerRepository.deleteById(newCustomer.getCustomerId());
    }

    @Test
    void delete() {
        Customer customer = new Customer(4L, "Stavros", "Kosmapetris");
        Customer newCustomer = customerRepository.save(customer);
        customerRepository.deleteById(newCustomer.getCustomerId());
        assertFalse(customerRepository.existsById(newCustomer.getCustomerId()));
    }

    @Test
    void myChapterees() {
        assertEquals(2 , customerRepository.myChapterees(2L).size());
    }
}
