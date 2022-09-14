package com.agileactors.chapteree_app.exceptions;


import com.agileactors.chapteree_app.controller.ChaptereeController;
import com.agileactors.chapteree_app.exception.InvalidIdException;
import com.agileactors.chapteree_app.exception.InvalidLevelException;
import com.agileactors.chapteree_app.model.Chapteree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-flyway_test.properties")
public class ChaptereeExceptionsTest {


        @Autowired
        private ChaptereeController chaptereeController;

        @Test
        void getByInvalidId() {
            assertThrows(InvalidIdException.class,
                    () -> chaptereeController.get(7L));
        }

    @Test
    void saveByInvalidLevel() {
        assertThrows(InvalidLevelException.class,
                () -> chaptereeController.create(new Chapteree(7L, "Orestis", "Pyrosvestis", "PHPing", "LOWW")));
    }



}
