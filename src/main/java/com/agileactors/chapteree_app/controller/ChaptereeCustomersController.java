package com.agileactors.chapteree_app.controller;


import com.agileactors.chapteree_app.repository.ChaptereeCustomerRepository;
import com.agileactors.chapteree_app.service.ChaptereeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping("api/chapteree/customers")
public class ChaptereeCustomersController {

    @Autowired
    private ChaptereeCustomerRepository chaptereeCustomerRepository; //change to Service
    @Autowired
    ChaptereeService chaptereeService;

    //getById
    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return chaptereeService.getChaptereeCustomers(id);
    }

    //findAll
    @GetMapping
    public List<List<String>> list() {
        return chaptereeService.listChaptereeCustomers();
    }

}
