package com.agileactors.chapteree_app.integrationtest;


import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.model.ChaptereeCustomer;
import com.agileactors.chapteree_app.model.ChaptereeCustomerId;
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


import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith({DBUnitExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
public class ChaptereesToCustomersIntegrationTest extends ChaptereesToCustomersBaseIntegrity {
    @Autowired
    private MockMvc mockMvc;
    ChaptereeCustomer nChaptereeCustomer;

        @BeforeEach
        public void init(){
            Customer nCustomer = customerService.save(customer);
            Chapteree nChapteree = chaptereeService.save(chapteree);
            customerId = nCustomer.getCustomerId();
            chaptereeId = nChapteree.getChaptereeId();
            ChaptereeCustomer chaptereeCustomer = new ChaptereeCustomer(chaptereeId,customerId);
            nChaptereeCustomer = chaptereeCustomerRepository.save(chaptereeCustomer);
        }

    @AfterEach
    public void tearDown(){
        customerService.deleteById(customerId);
        chaptereeService.deleteById(chaptereeId);
        ChaptereeCustomerId chaptereeCustomerId = new ChaptereeCustomerId(chaptereeId, customerId);
        chaptereeCustomerRepository.deleteById(chaptereeCustomerId);
    }

        @Test
        @Transactional
        public void getCustomerChaptereesById() throws Exception {

            mockMvc.perform(MockMvcRequestBuilders.get(CUSTOMER_CHAPTEREES_BASE_ENDPOINT+String.valueOf(nChaptereeCustomer.getCustomerId())))
                    .andExpect(status().isOk())
                    .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(content().string("[\"Customer: "+String.valueOf(nChaptereeCustomer.getCustomerId())+
                            ",Stavros,Kosmapetris\",\"Chapteree: "+String.valueOf(nChaptereeCustomer.getChaptereeId())+",Enas,Dyos,Java,HIGH,null\"]"));
        }

    @Test
    @Transactional
    public void getChaptereeCustomersById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(CHAPTEREE_CUSTOMERS_BASE_ENDPOINT+String.valueOf(nChaptereeCustomer.getChaptereeId())))
                .andExpect(status().isOk())
                .andExpect( content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("[\"Chapteree: "+String.valueOf(nChaptereeCustomer.getChaptereeId())+
                        ",Enas,Dyos,Java,HIGH,null\",\"Customer: "+String.valueOf(nChaptereeCustomer.getCustomerId())+",Stavros,Kosmapetris\"]"));
    }


    @Test
    @Transactional
    public void getCustomerChaptereesNotFound() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get(CUSTOMER_CHAPTEREES_BASE_ENDPOINT + "1000"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void getChaptereeCustomersNotFound() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get(CHAPTEREE_CUSTOMERS_BASE_ENDPOINT + "1000"))
                .andExpect(status().isNotFound());
    }

}
