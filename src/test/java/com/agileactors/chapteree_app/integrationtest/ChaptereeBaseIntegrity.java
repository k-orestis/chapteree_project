package com.agileactors.chapteree_app.integrationtest;

import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.service.ChaptereeService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;


public class ChaptereeBaseIntegrity {
    public static final String BASE_ENDPOINT = "http://localhost:8080/api/chapteree/";

    @Autowired
    protected ChaptereeService chaptereeService;
    public Long id;
    Chapteree chapteree;
    @BeforeEach
    public void setup(){
        chapteree= new Chapteree(22L, "Stratos", "Kosmapetris", "agiledev", "HIGH");
    }
}
