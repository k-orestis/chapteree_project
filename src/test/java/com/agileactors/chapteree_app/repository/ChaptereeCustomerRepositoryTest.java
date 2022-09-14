package com.agileactors.chapteree_app.repository;

import com.agileactors.chapteree_app.model.ChaptereeCustomer;
import com.agileactors.chapteree_app.model.ChaptereeCustomerId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-flyway_test.properties")
public class ChaptereeCustomerRepositoryTest {
    @Autowired
    ChaptereeCustomerRepository chaptereeCustomerRepository;

    @Test
    void findAll() {
        List<ChaptereeCustomer> chaptereeCustomers = chaptereeCustomerRepository.findAll();
        assertEquals(3, chaptereeCustomers.size() , "We should have 3 relations!");
    }

    @Test
    @Transactional
    void getOne() {
        ChaptereeCustomer chapCustomer = chaptereeCustomerRepository.getOne(new ChaptereeCustomerId(1L, 2L));
        assertEquals(1L, chapCustomer.getChaptereeId());
        assertEquals(2L, chapCustomer.getCustomerId());
    }

    @Test
    void saveDelete() {
        ChaptereeCustomerId chaptereeCustomerId = new ChaptereeCustomerId(5L,2L);
        ChaptereeCustomer newChapCustomer =
                chaptereeCustomerRepository.save(new ChaptereeCustomer(5L, 2L));
        assertEquals(5L, newChapCustomer.getChaptereeId());
        assertEquals(2L, newChapCustomer.getCustomerId());
        chaptereeCustomerRepository.deleteById(chaptereeCustomerId);
        assertFalse(chaptereeCustomerRepository.existsById(chaptereeCustomerId));
    }


}
