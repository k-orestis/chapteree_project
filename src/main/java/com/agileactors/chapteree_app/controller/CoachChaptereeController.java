package com.agileactors.chapteree_app.controller;

import com.agileactors.chapteree_app.exception.InvalidIdException;
import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.model.CoachChapteree;
import com.agileactors.chapteree_app.model.Ids;
import com.agileactors.chapteree_app.service.CoachChaptereeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/coach")
public class CoachChaptereeController {
    private CoachChaptereeService coachChaptereeService;

    @Autowired
    public CoachChaptereeController(CoachChaptereeService coachChaptereeService){
        this.coachChaptereeService = coachChaptereeService;
    }

    @GetMapping
    public List<CoachChapteree> list(){
        return coachChaptereeService.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        return coachChaptereeService.getById(id);

    }

    @GetMapping
    @RequestMapping("/chapterees/{id}")
    public List<Chapteree> getCoachee(@PathVariable Long id){
        if(coachChaptereeService.existsById(id)){
            return coachChaptereeService.findAllChapterees(id);
        }
        else throw new InvalidIdException();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Ids ids){
        return coachChaptereeService.create(ids);

    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Ids ids){
        return coachChaptereeService.put(id, ids);

    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id){
        return coachChaptereeService.deleteById(id);
    }
}
