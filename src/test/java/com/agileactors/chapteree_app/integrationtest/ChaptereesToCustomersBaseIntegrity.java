package com.agileactors.chapteree_app.integrationtest;

import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.model.ChaptereeCustomer;
import com.agileactors.chapteree_app.model.Customer;
import com.agileactors.chapteree_app.repository.ChaptereeCustomerRepository;
import com.agileactors.chapteree_app.service.ChaptereeService;
import com.agileactors.chapteree_app.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public class ChaptereesToCustomersBaseIntegrity {

    public static final String CHAPTEREE_CUSTOMERS_BASE_ENDPOINT = "http://localhost:8080/api/chapteree/customers/";
    public static final String CUSTOMER_CHAPTEREES_BASE_ENDPOINT = "http://localhost:8080/api/customer/chapterees/";


    @Autowired
    protected CustomerService customerService;
    @Autowired
    protected ChaptereeService chaptereeService;
    @Autowired
    protected ChaptereeCustomerRepository chaptereeCustomerRepository;

    Customer customer;
    Chapteree chapteree;
    ChaptereeCustomer chaptereeCustomer;
    public Long chaptereeId;
    public Long customerId;

    @BeforeEach
    public void setup(){
        customer = new Customer(4L, "Stavros", "Kosmapetris");
        chapteree = new Chapteree(4L, "Enas", "Dyos", "Java","HIGH");
        chaptereeCustomer = new ChaptereeCustomer(4L, 4L);
    }
}
