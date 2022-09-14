package com.agileactors.chapteree_app.controller;

import com.agileactors.chapteree_app.integrationtest.CustomerBaseIntegrity;
import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.model.ChaptereeCustomer;
import com.agileactors.chapteree_app.model.Customer;
import com.agileactors.chapteree_app.repository.ChaptereeCustomerRepository;
import com.agileactors.chapteree_app.service.ChaptereeService;
import com.agileactors.chapteree_app.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerChaptereesControllerTest extends CustomerBaseIntegrity {
    @SpyBean
    CustomerService customerService;
    @MockBean
    ChaptereeCustomerRepository chaptereeCustomerRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getCustomerChapterees() throws Exception {
        Customer customer = new Customer(5L, "Stavros", "Kosmapetris");
        List<String> newList = new ArrayList<>();
        newList.add("Customer: 5,Stavros,Kosmapetris");
        when(customerService.existsById(5L)).thenReturn(true);
        when(customerService.getOne(5L)).thenReturn(customer);
        when(customerService.myChapterees(5L)).thenReturn(newList);
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/customer/chapterees/"+"5"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("[\"Customer: 5,Stavros,Kosmapetris\"]"));
    }

    @Test
    @Transactional
    public void listAllCustomerChapterees() throws Exception {
        ChaptereeCustomer chaptereeCustomer = new ChaptereeCustomer(1L,2L);
        List<ChaptereeCustomer> chaptereesCustomers = List.of(chaptereeCustomer,chaptereeCustomer);
        when(chaptereeCustomerRepository.findAll()).thenReturn(chaptereesCustomers);
        List<String> newL= new ArrayList<>();
        newL.add("\"Customer: 2,Menelaos,Nikitakis\"");
        newL.add("\"Chapteree: 1,Kostas,Konstantinou,Java,MID,2\"");
        when(customerService.myChapterees(2L)).thenReturn(newL);
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/customer/chapterees/"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("[[\"\\\"Customer: 2,Menelaos,Nikitakis\\\"\",\"\\\"Chapteree: 1,Kostas,Konstantinou,Java,MID,2\\\"\"]]"));
    }

}
