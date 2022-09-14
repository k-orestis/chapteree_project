package com.agileactors.chapteree_app.controller;

import com.agileactors.chapteree_app.repository.ChaptereeCustomerRepository;
import com.agileactors.chapteree_app.service.ChaptereeService;
import com.agileactors.chapteree_app.service.CustomerService;
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
@RequestMapping("api/customer/chapterees")
public class CustomerChaptereesController {
    @Autowired
    private CustomerService customerService;


    //getById
    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        return customerService.getCustomerChapterees(id);
    }

    //findAll
    @GetMapping
    public List<List<String>> list(){
        return customerService.listCustomerChapterees();
    }
}
