package com.agileactors.chapteree_app.controller;



import com.agileactors.chapteree_app.integrationtest.CustomerBaseIntegrity;
import com.agileactors.chapteree_app.integrationtest.ResponseUtils;


import com.agileactors.chapteree_app.model.Customer;
import com.agileactors.chapteree_app.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest extends CustomerBaseIntegrity {
    @SpyBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void get() throws Exception {
        Customer customer = new Customer(4L, "Stavros", "Kosmapetris");
        List<Customer> customerList = List.of(customer, customer, customer);
        doReturn(customerList).when(customerService).findAll();

        mockMvc.perform(MockMvcRequestBuilders.get(CUSTOMER_BASE_ENDPOINT))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getById() throws Exception {
        Customer customer = new Customer(4L, "Stavros", "Kosmapetris");

        when(customerService.existsById(5L)).thenReturn(true);
        when(customerService.getOne(5L)).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get(CUSTOMER_BASE_ENDPOINT+"5"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Stavros"))
                .andExpect(jsonPath("$.lastName").value("Kosmapetris"));
    }


    @Test
    public void post() throws Exception{
        Customer postCustomer = new Customer(4L, "Stavros", "Kosmapetris");
        Customer mockCustomer = new Customer(4L, "Stavros", "Kosmapetris");
        doReturn(mockCustomer).when(customerService).save(any());
        mockMvc.perform(MockMvcRequestBuilders.post(CUSTOMER_BASE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ResponseUtils.asJsonString(postCustomer)))
                .andExpect(status().isCreated())
                .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Stavros"))
                .andExpect(jsonPath("$.lastName").value("Kosmapetris"));
    }
    @Test
    public void put() throws Exception{
        Customer putCustomer = new Customer(4L, "Stratos", "Kosmapetris");
        Customer mockCustomer = new Customer(4L, "Stavros", "Kosmapetris");
        doReturn(true).when(customerService).existsById(4L);

        doReturn(mockCustomer).when(customerService).update(any(), any());

        mockMvc.perform(MockMvcRequestBuilders.put(CUSTOMER_BASE_ENDPOINT + "4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ResponseUtils.asJsonString(putCustomer)))
                .andExpect(status().isOk())
                .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Stavros"))
                .andExpect(jsonPath("$.lastName").value("Kosmapetris"));
    }


    @Test
    public void delete() throws Exception {
        doReturn(true).when(customerService).existsById(3L);
        doNothing().when(customerService).deleteById(3L);
        mockMvc.perform(MockMvcRequestBuilders.delete(CUSTOMER_BASE_ENDPOINT + "3"))
                .andExpect(status().isOk());

    }
}
