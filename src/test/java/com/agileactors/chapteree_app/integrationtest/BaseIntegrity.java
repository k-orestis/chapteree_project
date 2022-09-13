package com.agileactors.chapteree_app.integrationtest;

import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.model.Customer;
import com.agileactors.chapteree_app.service.ChaptereeService;
import com.agileactors.chapteree_app.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseIntegrity {
    public static final String BASE_ENDPOINT = "http://localhost:8080/api/chapteree/";
    public static final String CUSTOMER_BASE_ENDPOINT = "http://localhost:8080/api/customer/";

    @Autowired
    protected ChaptereeService chaptereeService;
    @Autowired
    protected CustomerService customerService;
    public Long id;
    Chapteree chapteree;
    Customer customer;
    @BeforeEach
    public void setup(){
        chapteree = new Chapteree(22L, "Stratos", "Kosmapetris", "agiledev", "HIGH", null);
        customer = new Customer(4L, "Stavros", "Kosmapetris");
    }
}
