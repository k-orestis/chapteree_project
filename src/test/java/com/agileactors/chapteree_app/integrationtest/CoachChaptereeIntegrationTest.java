package com.agileactors.chapteree_app.integrationtest;

import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.model.Ids;
import com.agileactors.chapteree_app.service.ChaptereeService;
import com.github.database.rider.junit5.DBUnitExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({DBUnitExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
public class CoachChaptereeIntegrationTest extends CoachBaseIntegrity{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ChaptereeService chaptereeService;

    @Nested
    class TestSaveDelete {
        private Long idChap;
        private Long idCoach;
        @BeforeEach
        public void init(){
            idChap = chaptereeService.findAll()
                    .stream().filter(chap->chap.getLastName().equals("Pratikaki"))
                    .map(Chapteree::getChaptereeId)
                    .findFirst().get();
            idCoach = chaptereeService.findAll()
                    .stream().filter(chap->chap.getLastName().equals("Konstantinou"))
                    .map(Chapteree::getChaptereeId)
                    .findFirst().get();
        }
        @Test
        @Transactional
        public void getById() throws Exception {
            coachChaptereeService.save(coachChaptereeService.findById(idChap),idCoach);

            mockMvc.perform(MockMvcRequestBuilders.get(BASE_ENDPOINT+String.valueOf(idChap)))
                    .andExpect(status().isOk())
                    .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.chaptereeFirstName").value("Anna"))
                    .andExpect(jsonPath("$.chaptereeLastName").value("Pratikaki"))
                    .andExpect(jsonPath("$.chaptereeChapter").value("ArtAndMotion"))
                    .andExpect(jsonPath("$.coachId").value(idCoach));
        }
        @Test
        @Transactional
        public void put() throws Exception{
            coachChaptereeService.save(coachChaptereeService.findById(idChap),idCoach);

            mockMvc.perform(MockMvcRequestBuilders.put(BASE_ENDPOINT + String.valueOf(idChap))
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(ResponseUtils.asJsonString(new Ids(idChap,idCoach))))
                    .andExpect(status().isOk())
                    .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.chaptereeFirstName").value("Anna"))
                    .andExpect(jsonPath("$.coachChapter").value("Java"))
                    .andExpect(jsonPath("$.coachLevel").value("MID"));
        }

        @Test
        @Transactional
        public void post() throws Exception{

            mockMvc.perform(MockMvcRequestBuilders.post(BASE_ENDPOINT)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(ResponseUtils.asJsonString(new Ids(idChap, idCoach))))
                    .andExpect(status().isCreated())
                    .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.chaptereeFirstName").value("Anna"))
                    .andExpect(jsonPath("$.chaptereeLastName").value("Pratikaki"))
                    .andExpect(jsonPath("$.chaptereeChapter").value("ArtAndMotion"))
                    .andExpect(jsonPath("$.coachFirstName").value("Kostas"))
                    .andExpect(jsonPath("$.coachLastName").value("Konstantinou"))
                    .andExpect(jsonPath("$.coachChapter").value("Java"));

        }
        @AfterEach
        public void tearDown(){
            coachChaptereeService.deleteById(idChap);

        }

    }



    @Test
    public void getNotFound() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_ENDPOINT + "1000"))
                .andExpect(status().isNotFound());

    }





    @Test
    public void putNotFound() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.put(BASE_ENDPOINT + "1000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ResponseUtils.asJsonString(new Ids(1000L,22L))))
                .andExpect(status().isNotFound());

    }

    @Test
    @Transactional
    public void delete() throws Exception {
        Long idChap = chaptereeService.findAll()
                .stream().filter(chap->chap.getLastName().equals("Pratikaki"))
                .map(Chapteree::getChaptereeId)
                .findFirst().get();
        Long idCoach = chaptereeService.findAll()
                .stream().filter(chap->chap.getLastName().equals("Konstantinou"))
                .map(Chapteree::getChaptereeId)
                .findFirst().get();

        coachChaptereeService.save(coachChaptereeService.findById(idChap),idCoach);

        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_ENDPOINT + String.valueOf(idChap)))
                .andExpect(status().isOk());

    }
    @Test
    public void deleteNotFound() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_ENDPOINT + "1000"))
                .andExpect(status().isNotFound());

    }
}
