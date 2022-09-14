package com.agileactors.chapteree_app.controller;

import com.agileactors.chapteree_app.integrationtest.ChaptereeBaseIntegrity;
import com.agileactors.chapteree_app.integrationtest.ResponseUtils;
import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.service.ChaptereeService;
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
public class ChaptereeControllerTest extends ChaptereeBaseIntegrity {
    @SpyBean
    ChaptereeService chaptereeService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void get() throws Exception {
        Chapteree chapteree = new Chapteree(7L, "Stavros", "Kosmapetris", "node.js", "MID");
        List<Chapteree> chaptereeList = List.of(chapteree, chapteree, chapteree);
        doReturn(chaptereeList).when(chaptereeService).findAll();

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_ENDPOINT))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getById() throws Exception {
        Chapteree chapteree = new Chapteree(5L, "Stavros", "Kosmapetris", "Testing",  "LOW");
        when(chaptereeService.existsById(5L)).thenReturn(true);
        when(chaptereeService.getOne(5L)).thenReturn(chapteree);

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_ENDPOINT+"5"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Stavros"))
                .andExpect(jsonPath("$.lastName").value("Kosmapetris"))
                .andExpect(jsonPath("$.chapter").value("Testing"));
    }



    @Test
    public void post() throws Exception{
        Chapteree postChapteree = new Chapteree(7L, "Stavros", "Kosmapetris", "Business Analyst", "MID");
        Chapteree mockChapteree = new Chapteree(7L, "Stavros", "Kosmapetris", "Marketing",  "MID");
        doReturn(mockChapteree).when(chaptereeService).save(any());
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ResponseUtils.asJsonString(postChapteree)))
                .andExpect(status().isCreated())
                .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Stavros"))
                .andExpect(jsonPath("$.lastName").value("Kosmapetris"))
                .andExpect(jsonPath("$.chapter").value("Marketing"))
                .andExpect(jsonPath("$.level").value("MID"));

    }
    @Test
    public void put() throws Exception{
        Chapteree putChapteree = new Chapteree(7L, "Stratos", "Kosmapetris", "python",  "MID");
        Chapteree mockChapteree = new Chapteree(7L, "Stavros", "Kosmapetris", "front-end", "LOW");
        doReturn(true).when(chaptereeService).existsById(7L);
        doReturn(mockChapteree).when(chaptereeService).update(any(), any());
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_ENDPOINT + "7")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ResponseUtils.asJsonString(putChapteree)))
                .andExpect(status().isOk())
                .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Stavros"))
                .andExpect(jsonPath("$.lastName").value("Kosmapetris"))
                .andExpect(jsonPath("$.chapter").value("front-end"))
                .andExpect(jsonPath("$.level").value("LOW"));

    }

    @Test
    public void delete() throws Exception {
        doReturn(true).when(chaptereeService).existsById(5L);
        doNothing().when(chaptereeService).deleteById(5L);
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_ENDPOINT + "5"))
                .andExpect(status().isOk());

    }




}
