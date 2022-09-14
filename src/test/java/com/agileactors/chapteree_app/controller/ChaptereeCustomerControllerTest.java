package com.agileactors.chapteree_app.controller;

import com.agileactors.chapteree_app.integrationtest.ChaptereeCustomerBaseIntegrity;
import com.agileactors.chapteree_app.integrationtest.ResponseUtils;
import com.agileactors.chapteree_app.model.ChaptereeCustomer;
import com.agileactors.chapteree_app.model.ChaptereeCustomerId;
import com.agileactors.chapteree_app.service.ChaptereeCustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ChaptereeCustomerControllerTest extends ChaptereeCustomerBaseIntegrity {
    @SpyBean
    ChaptereeCustomerService chaptereeCustomerService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void post() throws Exception{
        ChaptereeCustomerId chaptereeCustomerId = new ChaptereeCustomerId(22L, 11L);
        when(chaptereeCustomerService.existsById(chaptereeCustomerId)).thenReturn(true);
        doReturn(new ChaptereeCustomer(22L, 11L)).when(chaptereeCustomerService).save(chaptereeCustomerId);
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ResponseUtils.asJsonString(new ChaptereeCustomer(22L, 11L))))
                .andExpect(status().isCreated())
                .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.chaptereeId").value("22"))
                .andExpect(jsonPath("$.customerId").value("11"));

    }

    @Test
    public void delete() throws Exception {
        ChaptereeCustomerId chaptereeCustomerId = new ChaptereeCustomerId(22L, 11L);
        doReturn(true).when(chaptereeCustomerService).existsById(chaptereeCustomerId);
        doNothing().when(chaptereeCustomerService).delete(chaptereeCustomerId);
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_ENDPOINT + "22").contentType(MediaType.APPLICATION_JSON)
                        .content(ResponseUtils.asJsonString(new ChaptereeCustomer(22L, 11L))))
                .andExpect(status().isOk());

    }

}
