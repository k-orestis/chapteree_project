package com.agileactors.chapteree_app.integrationtest;

import com.agileactors.chapteree_app.model.Customer;
import com.github.database.rider.junit5.DBUnitExtension;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;


import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith({DBUnitExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerIntegrationTest extends CustomerBaseIntegrity{
    @Autowired
    private MockMvc mockMvc;

    @Nested
    class TestSaveDelete {
        @BeforeEach
        public void init(){
            Customer nCustomer = customerService.save(customer);
            id = nCustomer.getCustomerId();
        }
        @Test
        public void getById() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders.get(CUSTOMER_BASE_ENDPOINT+String.valueOf(id)))
                    .andExpect(status().isOk())
                    .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.firstName").value("Stavros"))
                    .andExpect(jsonPath("$.lastName").value("Kosmapetris"));
        }
        @Test
        public void put() throws Exception{

            Customer customerUpdated =  new Customer(id, "Stavroulis", "Kosmapetris");

            mockMvc.perform(MockMvcRequestBuilders.put(CUSTOMER_BASE_ENDPOINT + String.valueOf(id))
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(ResponseUtils.asJsonString(customerUpdated)))
                    .andExpect(status().isOk())
                    .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.firstName").value("Stavroulis"))
                    .andExpect(jsonPath("$.lastName").value("Kosmapetris"));
        }
        @AfterEach
        public void tearDown(){
            customerService.deleteById(id);
        }

    }



    @Test
    public void getNotFound() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get(CUSTOMER_BASE_ENDPOINT + "1000"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void post() throws Exception{
        customer = new Customer(22L, "Stratos", "Kosmapetris");
        mockMvc.perform(MockMvcRequestBuilders.post(CUSTOMER_BASE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ResponseUtils.asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Stratos"))
                .andExpect(jsonPath("$.lastName").value("Kosmapetris"));

        customerService.deleteById(customerService.findAll()
                .stream().map(Customer::getCustomerId)
                .max(Long::compare).get());

    }



    @Test
    public void putNotFound() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.put(CUSTOMER_BASE_ENDPOINT + "1000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ResponseUtils.asJsonString(customer)))
                .andExpect(status().isNotFound());

    }

    @Test
    public void delete() throws Exception {
        Customer newCustomer = customerService.save(customer);
        id = newCustomer.getCustomerId();
        mockMvc.perform(MockMvcRequestBuilders.delete(CUSTOMER_BASE_ENDPOINT + String.valueOf(id)))
                .andExpect(status().isOk());

    }
    @Test
    public void deleteNotFound() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(CUSTOMER_BASE_ENDPOINT + "1000"))
                .andExpect(status().isNotFound());

    }

}
