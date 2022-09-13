package com.agileactors.chapteree_app.service;

import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.model.CoachChapteree;
import com.agileactors.chapteree_app.repository.ChaptereeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class CoachChaptereeServiceTest {

    public static final long CHAPTEREE_ID = 10L;
    private CoachChaptereeService coachChaptereeService;
    private ChaptereeRepository chaptereeRepository = mock(ChaptereeRepository.class);

    private Chapteree chapteree1, chapteree2;

    @BeforeEach
    void setUp() {
        coachChaptereeService = new CoachChaptereeService(chaptereeRepository);
        chapteree1 = new Chapteree(10L, "John", "Alogoouras",
                "Java", "MID", 4L, 2L);
        chapteree2 = new Chapteree(4L, "George", "Alogoouras",
                "Python", "MID", 10L, 2L);
    }
    @Test
    void findAll() {
        List<Chapteree> chapterees = List.of(chapteree1, chapteree2);
        when(chaptereeRepository.findAll()).thenReturn(chapterees);
        when(chaptereeRepository.getOne(chapteree1.getChaptereeId())).thenReturn(chapteree1);
        when(chaptereeRepository.getOne(chapteree1.getCoachId())).thenReturn(chapteree2);
        when(chaptereeRepository.getOne(chapteree2.getChaptereeId())).thenReturn(chapteree2);
        when(chaptereeRepository.getOne(chapteree2.getCoachId())).thenReturn(chapteree1);
        List<CoachChapteree> coachChaptereesList = coachChaptereeService.findAll();
        assertEquals(chapterees.get(0).getFirstName(), coachChaptereesList.get(0).getChaptereeFirstName());
        assertEquals(chapterees.get(0).getLastName(), coachChaptereesList.get(0).getChaptereeLastName());
        assertEquals(chapterees.get(0).getChapter(), coachChaptereesList.get(0).getChaptereeChapter());
        assertEquals(chapterees.get(0).getLevel(), coachChaptereesList.get(0).getChaptereeLevel());
        assertEquals(chapterees.get(1).getFirstName(), coachChaptereesList.get(0).getCoachFirstName());
        assertEquals(chapterees.get(1).getLastName(), coachChaptereesList.get(0).getCoachLastName());
        assertEquals(chapterees.get(1).getChapter(), coachChaptereesList.get(0).getCoachChapter());
        assertEquals(chapterees.get(1).getLevel(), coachChaptereesList.get(0).getCoachLevel());
    }

    @Test
    void get() {
        when(chaptereeRepository.getOne(CHAPTEREE_ID)).thenReturn(chapteree1);
        Chapteree newChapteree = coachChaptereeService.getOne(CHAPTEREE_ID);
        assertEquals(chapteree1.getFirstName(), newChapteree.getFirstName());
        assertEquals(chapteree1.getLastName(), newChapteree.getLastName());
        assertEquals(chapteree1.getChapter(), newChapteree.getChapter());
        assertEquals(chapteree1.getLevel(), newChapteree.getLevel());
        assertEquals(chapteree1.getCoachId(), newChapteree.getCoachId());
    }
    @Test
    void getCoach() {
        when(chaptereeRepository.getOne(CHAPTEREE_ID)).thenReturn(chapteree1);
        when(chaptereeRepository.getOne(chapteree1.getCoachId())).thenReturn(chapteree2);
        CoachChapteree newCoachChapteree = coachChaptereeService.getCoach(CHAPTEREE_ID);
        assertEquals(chapteree2.getFirstName(), newCoachChapteree.getCoachFirstName());
        assertEquals(chapteree2.getLastName(), newCoachChapteree.getCoachLastName());
        assertEquals(chapteree2.getChapter(), newCoachChapteree.getCoachChapter());
        assertEquals(chapteree2.getLevel(), newCoachChapteree.getCoachLevel());
    }

    @Test
    void save() {
        when(chaptereeRepository.save(chapteree1)).thenReturn(chapteree1);
        when(chaptereeRepository.getOne(chapteree1.getChaptereeId())).thenReturn(chapteree1);
        when(chaptereeRepository.getOne(4L)).thenReturn(chapteree2);
        CoachChapteree newCoachChapteree = coachChaptereeService.save(chapteree1, 4L);
        assertEquals(chapteree2.getFirstName(), newCoachChapteree.getCoachFirstName());
        assertEquals(chapteree2.getLastName(), newCoachChapteree.getCoachLastName());
        assertEquals(chapteree2.getChapter(), newCoachChapteree.getCoachChapter());
        assertEquals(chapteree2.getLevel(), newCoachChapteree.getCoachLevel());
    }


    @Test
    void update() {
        Chapteree newChapteree = new Chapteree(10L, "John", "Alogoouras", "Java", "MID");
        when(chaptereeRepository.getOne(CHAPTEREE_ID)).thenReturn(chapteree1);
        when(chaptereeRepository.save(chapteree1)).thenReturn(newChapteree);

        Chapteree updatedChapteree = coachChaptereeService.update(CHAPTEREE_ID, newChapteree);

        assertEquals(newChapteree.getFirstName(), updatedChapteree.getFirstName());
        assertEquals(newChapteree.getLastName(), updatedChapteree.getLastName());
        assertEquals(newChapteree.getChapter(), updatedChapteree.getChapter());
        assertEquals(newChapteree.getLevel(), updatedChapteree.getLevel());
    }
}
