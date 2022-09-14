package com.agileactors.chapteree_app.service;

import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.repository.ChaptereeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

public class ChaptereeServiceTest {

    public static final long CHAPTEREE_ID = 0L;
    private ChaptereeService chaptereeService;
    private ChaptereeRepository chaptereeRepository = mock(ChaptereeRepository.class);

    private Chapteree chapteree;

    @BeforeEach
    void setUp() {
        chaptereeService = new ChaptereeService(chaptereeRepository);
        chapteree = new Chapteree(10L, "John", "Alogoouras", "Java", "MID");
    }

    @Test
    void findAll() {
        List<Chapteree> chapterees = List.of(chapteree, chapteree);
        when(chaptereeRepository.findAll()).thenReturn(chapterees);
        List<Chapteree> chaptereesList = chaptereeService.findAll();
        assertEquals(chapterees.get(0).getFirstName(), chaptereesList.get(0).getFirstName());
        assertEquals(chapterees.get(0).getLastName(), chaptereesList.get(0).getLastName());
        assertEquals(chapterees.get(0).getChapter(), chaptereesList.get(0).getChapter());
        assertEquals(chapterees.get(0).getLevel(), chaptereesList.get(0).getLevel());
    }

    @Test
    void get() {
        when(chaptereeRepository.getOne(CHAPTEREE_ID)).thenReturn(chapteree);
        Chapteree newChapteree = chaptereeService.getOne(CHAPTEREE_ID);
        assertEquals(chapteree.getFirstName(), newChapteree.getFirstName());
        assertEquals(chapteree.getLastName(), newChapteree.getLastName());
        assertEquals(chapteree.getChapter(), newChapteree.getChapter());
        assertEquals(chapteree.getLevel(), newChapteree.getLevel());
    }

    @Test
    void save() {
        when(chaptereeRepository.save(chapteree)).thenReturn(chapteree);
        Chapteree newChapteree = chaptereeService.save(chapteree);
        assertEquals(chapteree.getFirstName(), newChapteree.getFirstName());
        assertEquals(chapteree.getLastName(), newChapteree.getLastName());
        assertEquals(chapteree.getChapter(), newChapteree.getChapter());
        assertEquals(chapteree.getLevel(), newChapteree.getLevel());
    }

    @Test
    void delete() {
        chaptereeService.deleteById(CHAPTEREE_ID);
        verify(chaptereeRepository).deleteById(CHAPTEREE_ID);
    }

    @Test
    void update() {
        Chapteree newChapteree = new Chapteree(10L, "John", "Alogoouras", "Java", "MID");
        when(chaptereeRepository.getOne(CHAPTEREE_ID)).thenReturn(chapteree);
        when(chaptereeRepository.save(chapteree)).thenReturn(newChapteree);

        Chapteree updatedChapteree = chaptereeService.update(CHAPTEREE_ID, newChapteree);

        assertEquals(newChapteree.getFirstName(), updatedChapteree.getFirstName());
        assertEquals(newChapteree.getLastName(), updatedChapteree.getLastName());
        assertEquals(newChapteree.getChapter(), updatedChapteree.getChapter());
        assertEquals(newChapteree.getLevel(), updatedChapteree.getLevel());
    }

    @Test
    void myCustomers() {
        when(chaptereeRepository.getOne(CHAPTEREE_ID)).thenReturn(chapteree);
        when(chaptereeRepository.myCustomers(CHAPTEREE_ID)).thenReturn(new ArrayList<>());
        assertEquals(1,chaptereeService.myCustomers(CHAPTEREE_ID).size());
    }
}
