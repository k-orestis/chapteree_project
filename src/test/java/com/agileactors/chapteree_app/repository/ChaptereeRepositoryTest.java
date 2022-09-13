package com.agileactors.chapteree_app.repository;

import com.agileactors.chapteree_app.model.Chapteree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


import javax.transaction.Transactional;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application_test.properties")
public class ChaptereeRepositoryTest {

    @Autowired
    ChaptereeRepository chaptereeRepository;

    @Test
    void findAll() {
        List<Chapteree> chapterees = chaptereeRepository.findAll();
        assertEquals(6, chapterees.size() , "We should have 6 chapterees!");
    }

    @Test
    @Transactional
    void getOne() {
        Chapteree chapteree = chaptereeRepository.getOne(1L);
        assertEquals("Kostas", chapteree.getFirstName());
        assertEquals("Konstantinou", chapteree.getLastName());
        assertEquals("Java", chapteree.getChapter());
        assertEquals("MID", chapteree.getLevel());
    }

    @Test
    void save() {
        Chapteree chapteree = new Chapteree(7L, "Stavros", "Kosmapetraros", "Golang", "HIGH");
        Chapteree newChapteree = chaptereeRepository.save(chapteree);
        assertEquals("Stavros", newChapteree.getFirstName());
        assertEquals("Kosmapetraros", newChapteree.getLastName());
        assertEquals("Golang", newChapteree.getChapter());
        assertEquals("HIGH", newChapteree.getLevel());
        chaptereeRepository.deleteById(newChapteree.getChaptereeId());
    }

    @Test
    void delete() {
        Chapteree chapteree = new Chapteree(7L, "Stavros", "Kosmapetraros", "Golang", "HIGH");
        Chapteree newChapteree = chaptereeRepository.save(chapteree);
        chaptereeRepository.deleteById(newChapteree.getChaptereeId());
        assertFalse(chaptereeRepository.existsById(newChapteree.getChaptereeId()));
    }
}
