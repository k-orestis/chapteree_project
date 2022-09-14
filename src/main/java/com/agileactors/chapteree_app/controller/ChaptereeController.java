package com.agileactors.chapteree_app.controller;

import com.agileactors.chapteree_app.exception.InvalidIdException;
import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.service.ChaptereeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLOutput;
import java.util.List;

@Controller
@RestController
@RequestMapping("api/chapteree")
public class ChaptereeController {

    private ChaptereeService chaptereeService;

    @Autowired
    public ChaptereeController(ChaptereeService chaptereeService) {
        this.chaptereeService = chaptereeService;
    }

    @GetMapping
    public List<Chapteree> list(){
        return chaptereeService.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        return chaptereeService.get(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Chapteree chapteree){
        return chaptereeService.create(chapteree);
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id){
        return chaptereeService.delete(id);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Chapteree chapteree){
        return chaptereeService.put(id, chapteree);
    }


}
