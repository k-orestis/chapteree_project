package com.agileactors.chapteree_app.integrationtest;

import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.model.Customer;
import org.junit.jupiter.api.BeforeEach;



public class ChaptereeCustomerBaseIntegrity {
    public static final String BASE_ENDPOINT = "http://localhost:8080/api/customer_chapterees/";
    protected Chapteree chapteree;
    protected Customer customer;
    @BeforeEach
    public void setup(){
        chapteree= new Chapteree(22L, "Stratos",
                "Kosmapetris", "agiledev", "HIGH", null);
        customer= new Customer(11L, "Stavros",
                "Kosmapetran");
    }
}
