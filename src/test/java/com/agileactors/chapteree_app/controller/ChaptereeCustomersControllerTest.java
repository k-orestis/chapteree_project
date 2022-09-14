package com.agileactors.chapteree_app.controller;

import com.agileactors.chapteree_app.integrationtest.ChaptereeBaseIntegrity;
import com.agileactors.chapteree_app.integrationtest.ResponseUtils;
import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.model.ChaptereeCustomer;
import com.agileactors.chapteree_app.repository.ChaptereeCustomerRepository;
import com.agileactors.chapteree_app.service.ChaptereeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
public class ChaptereeCustomersControllerTest extends ChaptereeBaseIntegrity {
    @SpyBean
    ChaptereeService chaptereeService;
    @MockBean
    ChaptereeCustomerRepository chaptereeCustomerRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getById() throws Exception {
        Chapteree chapteree = new Chapteree(5L, "Stavros", "Kosmapetris", "Testing",  "LOW");
        List<String> newList = new ArrayList<>();
        newList.add("Chapteree: 5,Stavros,Kosmapetris,Testing,LOW,null");
        when(chaptereeService.existsById(5L)).thenReturn(true);
        when(chaptereeService.getOne(5L)).thenReturn(chapteree);
        when(chaptereeService.myCustomers(5L)).thenReturn(newList);
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/chapteree/customers/"+"5"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("[\"Chapteree: 5,Stavros,Kosmapetris,Testing,LOW,null\"]"));
    }

    @Test
    @Transactional
    public void listAllChaptereeCustomers() throws Exception {
        ChaptereeCustomer chaptereeCustomer = new ChaptereeCustomer(1L,2L);
        List<ChaptereeCustomer> chaptereesCustomers = List.of(chaptereeCustomer,chaptereeCustomer);
        when(chaptereeCustomerRepository.findAll()).thenReturn(chaptereesCustomers);
        List<String> newL= new ArrayList<>();
        newL.add("\"Chapteree: 1,Kostas,Konstantinou,Java,MID,2\"");
        newL.add("\"Customer: 2,Menelaos,Nikitakis\"");
        when(chaptereeService.myCustomers(1L)).thenReturn(newL);
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/chapteree/customers/"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("[[\"\\\"Chapteree: 1,Kostas,Konstantinou,Java,MID,2\\\"\",\"\\\"Customer: 2,Menelaos,Nikitakis\\\"\"]]"));
    }

}
