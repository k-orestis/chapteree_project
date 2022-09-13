package com.agileactors.chapteree_app.integrationtest;

import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.service.CoachChaptereeService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public class CoachBaseIntegrity {
        public static final String BASE_ENDPOINT = "http://localhost:8080/api/coach/";

        @Autowired
        protected CoachChaptereeService coachChaptereeService;
        public Long id;
        protected Chapteree chapteree1, chapteree2;
        @BeforeEach
        public void setup(){
            chapteree1= new Chapteree(22L, "Stratos",
                    "Kosmapetris", "agiledev", "HIGH", null);
            chapteree2= new Chapteree(11L, "Stavros",
                    "Kosmapetran", "agiledevops", "LOW", null);
        }

    }
