package com.agileactors.chapteree_app.exceptions;

import com.agileactors.chapteree_app.controller.CustomerController;
import com.agileactors.chapteree_app.exception.InvalidIdException;
import com.agileactors.chapteree_app.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-flyway_test.properties")
public class CustomerExceptionsTest {


    @Autowired
    private CustomerController customerController;

    @Test
    void getByInvalidId() {
        assertThrows(InvalidIdException.class,
                () -> customerController.get(7L));
    }

    @Test
    void deleteByInvalidId() {
        assertThrows(InvalidIdException.class,
                () -> customerController.delete(7L));
    }

    @Test
    void updateByInvalidId() {
        assertThrows(InvalidIdException.class,
                () -> customerController.update(10L, new Customer(10L, "Taki", "Tsan")));
    }
}