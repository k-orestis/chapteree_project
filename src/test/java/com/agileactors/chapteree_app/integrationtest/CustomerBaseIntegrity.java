package com.agileactors.chapteree_app.integrationtest;

import com.agileactors.chapteree_app.model.Customer;
import com.agileactors.chapteree_app.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerBaseIntegrity {
    public static final String CUSTOMER_BASE_ENDPOINT = "http://localhost:8080/api/customer/";


    @Autowired
    protected CustomerService customerService;

    Customer customer;
    public Long id;
    @BeforeEach
    public void setup(){
        customer = new Customer(4L, "Stavros", "Kosmapetris");
    }
}
