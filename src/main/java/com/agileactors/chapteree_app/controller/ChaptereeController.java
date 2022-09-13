package com.agileactors.chapteree_app.controller;

import com.agileactors.chapteree_app.exception.InvalidIdException;
import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.service.ChaptereeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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
        if(chaptereeService.existsById(id)){
            Chapteree chap = chaptereeService.getOne(id);
            try {
                return ResponseEntity.ok().location(new URI(String.valueOf(chap.getChaptereeId())))
                        .body(chap);
            } catch (URISyntaxException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        }
        else throw new InvalidIdException();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Chapteree chapteree){
       Chapteree newChap = chaptereeService.save(chapteree);
        try{
            return ResponseEntity.created(new URI(String.valueOf(newChap.getChaptereeId())))
                    .body(newChap);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(chaptereeService.existsById(id)){
            chaptereeService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else throw new InvalidIdException();
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Chapteree chapteree){
        if(chaptereeService.existsById(id)){
           Chapteree updatedChap = chaptereeService.update(id, chapteree);
            try {
                return ResponseEntity.ok().location(new URI(String.valueOf(updatedChap.getChaptereeId())))
                        .body(updatedChap);
            } catch (URISyntaxException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        else throw new InvalidIdException();
    }


}
