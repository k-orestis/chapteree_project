package com.agileactors.chapteree_app.controller;

import com.agileactors.chapteree_app.model.ChaptereeCustomerId;
import com.agileactors.chapteree_app.service.ChaptereeCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/customer_chapterees")
public class ChaptereeCustomerController {
    private ChaptereeCustomerService chaptereeCustomerService;

    @Autowired
    public ChaptereeCustomerController(ChaptereeCustomerService chaptereeCustomerService) {
        this.chaptereeCustomerService = chaptereeCustomerService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ChaptereeCustomerId chaptereeCustomerId){
        return chaptereeCustomerService.create(chaptereeCustomerId);

    }


    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestBody ChaptereeCustomerId chaptereeCustomerId){
        return chaptereeCustomerService.deleteById(id, chaptereeCustomerId);
    }

}
