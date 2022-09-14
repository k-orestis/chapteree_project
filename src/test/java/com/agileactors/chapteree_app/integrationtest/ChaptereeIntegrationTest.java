package com.agileactors.chapteree_app.integrationtest;

import com.agileactors.chapteree_app.model.Chapteree;
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
public class ChaptereeIntegrationTest extends ChaptereeBaseIntegrity {
    @Autowired
    private MockMvc mockMvc;

    @Nested
    class TestSaveDelete {
        @BeforeEach
        public void init(){
            Chapteree nChapteree = chaptereeService.saveAndFlush(chapteree);
            id = nChapteree.getChaptereeId();
        }
        @Test
        public void getById() throws Exception {


            mockMvc.perform(MockMvcRequestBuilders.get(BASE_ENDPOINT+String.valueOf(id)))
                    .andExpect(status().isOk())
                    .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.firstName").value("Stratos"))
                    .andExpect(jsonPath("$.lastName").value("Kosmapetris"))
                    .andExpect(jsonPath("$.chapter").value("agiledev"))
                    .andExpect(jsonPath("$.level").value("HIGH"));
        }
        @Test
        public void put() throws Exception{

            Chapteree chaptereeUpdated = new Chapteree(id, "Stavros", "Kosmapetris", "DevOps", "LOW");

            mockMvc.perform(MockMvcRequestBuilders.put(BASE_ENDPOINT + String.valueOf(id))
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(ResponseUtils.asJsonString(chaptereeUpdated)))
                    .andExpect(status().isOk())
                    .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.firstName").value("Stavros"))
                    .andExpect(jsonPath("$.chapter").value("DevOps"))
                    .andExpect(jsonPath("$.level").value("LOW"));

        }
        @AfterEach
        public void tearDown(){
            chaptereeService.deleteById(id);
        }

    }



    @Test
    public void getNotFound() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_ENDPOINT + "1000"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void post() throws Exception{
        chapteree = new Chapteree(22L, "Stratos", "Kosmapetris", "DBEngineering", "HIGH");
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ResponseUtils.asJsonString(chapteree)))
                .andExpect(status().isCreated())
                .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value("Stratos"))
                .andExpect(jsonPath("$.lastName").value("Kosmapetris"))
                .andExpect(jsonPath("$.chapter").value("DBEngineering"))
                .andExpect(jsonPath("$.level").value("HIGH"));

        chaptereeService.deleteById(chaptereeService.findAll()
                .stream().map(Chapteree::getChaptereeId)
                .max(Long::compare).get());

    }



    @Test
    public void putNotFound() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.put(BASE_ENDPOINT + "1000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ResponseUtils.asJsonString(chapteree)))
                .andExpect(status().isNotFound());

    }

    @Test
    public void delete() throws Exception {
        Chapteree nChapteree = chaptereeService.saveAndFlush(chapteree);
        id = nChapteree.getChaptereeId();
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_ENDPOINT + String.valueOf(id)))
                .andExpect(status().isOk());

    }
    @Test
    public void deleteNotFound() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_ENDPOINT + "1000"))
                .andExpect(status().isNotFound());

    }

}
