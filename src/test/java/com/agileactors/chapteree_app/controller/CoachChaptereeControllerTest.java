package com.agileactors.chapteree_app.controller;

import com.agileactors.chapteree_app.integrationtest.CoachBaseIntegrity;
import com.agileactors.chapteree_app.integrationtest.ResponseUtils;
import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.model.CoachChapteree;
import com.agileactors.chapteree_app.model.Ids;
import com.agileactors.chapteree_app.service.ChaptereeService;
import com.agileactors.chapteree_app.service.CoachChaptereeService;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CoachChaptereeControllerTest extends CoachBaseIntegrity {
    @SpyBean
    CoachChaptereeService coachChaptereeService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void get() throws Exception {
        Chapteree chapteree = new Chapteree(7L, "Stavros", "Kosmapetris", "node.js", "MID", 2L);
        List<Chapteree> chaptereeList = List.of(chapteree, chapteree, chapteree);
        doReturn(chaptereeList).when(coachChaptereeService).findAll();

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_ENDPOINT))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getById() throws Exception {
        chapteree1.setCoachId(11L);
        when(coachChaptereeService.existsById(22L)).thenReturn(true);
        //when(coachChaptereeService.getCoach(22L)).thenReturn(new CoachChapteree(chapteree2, chapteree1));
       when(coachChaptereeService.getOne(22L)).thenReturn(chapteree1);
       when(coachChaptereeService.getOne(11L)).thenReturn(chapteree2);
       // when(coachChaptereeService.getCoach(22L)).thenReturn(new CoachChapteree(chapteree1, chapteree2));


        mockMvc.perform(MockMvcRequestBuilders.get(BASE_ENDPOINT+"22"))
                .andExpect(status().isOk())
                .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.chaptereeFirstName").value("Stratos"))
                .andExpect(jsonPath("$.chaptereeLastName").value("Kosmapetris"))
                .andExpect(jsonPath("$.coachFirstName").value("Stavros"))
                .andExpect(jsonPath("$.coachLastName").value("Kosmapetran"));
    }



    @Test
    public void post() throws Exception{
        when(coachChaptereeService.existsById(11L)).thenReturn(true);
        when(coachChaptereeService.existsById(22L)).thenReturn(true);
        when(coachChaptereeService.getOne(22L)).thenReturn(chapteree1);
        doReturn(new CoachChapteree(chapteree2, chapteree1)).when(coachChaptereeService).save(chapteree1, 11L);
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ResponseUtils.asJsonString(new Ids(22L, 11L))))
                .andExpect(status().isCreated())
                .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.chaptereeFirstName").value("Stratos"))
                .andExpect(jsonPath("$.chaptereeLastName").value("Kosmapetris"))
                .andExpect(jsonPath("$.coachFirstName").value("Stavros"))
                .andExpect(jsonPath("$.coachLastName").value("Kosmapetran"));

    }
    @Test
    public void put() throws Exception{
        Chapteree putChapteree = new Chapteree(7L, "Stratos", "Kosmapetris", "python",  "MID");
        Chapteree mockChapteree = new Chapteree(7L, "Stavros", "Kosmapetris", "front-end", "LOW");
        doReturn(true).when(coachChaptereeService).existsById(22L);
        when(coachChaptereeService.getOne(22L)).thenReturn(chapteree1);
        doReturn(new CoachChapteree(chapteree2, chapteree1)).when(coachChaptereeService).save(chapteree1, 11L);
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_ENDPOINT + "22")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ResponseUtils.asJsonString(new Ids(22L, 11L))))
                .andExpect(status().isOk())
                .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.chaptereeFirstName").value("Stratos"))
                .andExpect(jsonPath("$.chaptereeLastName").value("Kosmapetris"))
                .andExpect(jsonPath("$.coachFirstName").value("Stavros"))
                .andExpect(jsonPath("$.coachLastName").value("Kosmapetran"));

    }


    @Test
    public void delete() throws Exception {
        doReturn(true).when(coachChaptereeService).existsById(22L);
        when(coachChaptereeService.getOne(22L)).thenReturn(chapteree1);
        doReturn(new CoachChapteree(null,chapteree1)).when(coachChaptereeService).save(chapteree1, null);
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_ENDPOINT + "22"))
                .andExpect(status().isOk());

    }

}
