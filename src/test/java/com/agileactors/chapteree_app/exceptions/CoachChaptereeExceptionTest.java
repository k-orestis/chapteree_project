package com.agileactors.chapteree_app.exceptions;

import com.agileactors.chapteree_app.controller.CoachChaptereeController;
import com.agileactors.chapteree_app.exception.NoCoachException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
//@TestPropertySource(locations = "classpath:application-flyway_test.properties")
public class CoachChaptereeExceptionTest {
    @Autowired
    private CoachChaptereeController coachChaptereeController;

    @Test
    @Transactional
    void getWithNoCoach() {
        assertThrows(NoCoachException.class,
                () -> coachChaptereeController.get(2L));
    }
}
