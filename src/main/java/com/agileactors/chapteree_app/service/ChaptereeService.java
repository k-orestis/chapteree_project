package com.agileactors.chapteree_app.service;

import com.agileactors.chapteree_app.exception.InvalidIdException;
import com.agileactors.chapteree_app.exception.InvalidLevelException;
import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.repository.ChaptereeCustomerRepository;
import com.agileactors.chapteree_app.repository.ChaptereeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChaptereeService {

    @Autowired
    ChaptereeRepository chaptereeRepository;

    @Autowired
    ChaptereeCustomerRepository chaptereeCustomerRepository;

    public ChaptereeService(ChaptereeRepository chaptereeRepository) { this.chaptereeRepository = chaptereeRepository; }

    //findAll
    public List<Chapteree> findAll() { return this.chaptereeRepository.findAll(); }

    //existsById
    public boolean existsById(long id) { return this.chaptereeRepository.existsById(id); }

    //getOne
    public Chapteree getOne(Long id) { return this.chaptereeRepository.getOne(id); }

    //saveAndFlush
    public Chapteree saveAndFlush(Chapteree chapteree) { return this.chaptereeRepository.saveAndFlush(chapteree); }

    //save
    public Chapteree save(Chapteree chapteree) {
        validateLevel(chapteree.getLevel());
        return this.chaptereeRepository.save(chapteree);
    }

    //deleteById
    public void deleteById(Long id) { this.chaptereeRepository.deleteById(id); }

    //update

    public Chapteree update(Long id, Chapteree chapteree) {
        Chapteree existingChapteree = getOne(id);
        BeanUtils.copyProperties(chapteree, existingChapteree, "chaptereeId");
        return save(existingChapteree);
    }

    private static void validateLevel(String lvl) {
        if(!EnumUtils.isValidEnum(Chapteree.Level.class, lvl)) throw new InvalidLevelException();
    }

    public List<String> myCustomers(Long id) {
        List<String> strL = new ArrayList<>();
        strL.add("Chapteree: "+id.toString()+","+this.getOne(id).getFirstName()+","+this.getOne(id).getLastName()
        +","+this.getOne(id).getChapter()+","+this.getOne(id).getLevel()+","+this.getOne(id).getCoachId());
        for (String str : chaptereeRepository.myCustomers(id)) {
            strL.add("Customer: "+str);
        }
        return strL;
    }

    //Requests
    public ResponseEntity<?> get(Long id){
        if(this.existsById(id)){
            Chapteree chapteree = this.getOne(id);
            try {
                return ResponseEntity.ok().location(new URI(String.valueOf(chapteree.getChaptereeId())))
                        .body(chapteree);
            } catch (URISyntaxException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        }
        else throw new InvalidIdException();
    }

    public ResponseEntity<?> create(Chapteree chapteree){
        Chapteree newChap = this.save(chapteree);
        try{
            return ResponseEntity.created(new URI(String.valueOf(newChap.getChaptereeId())))
                    .body(newChap);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> delete(Long id){
        if(this.existsById(id)){
            this.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else throw new InvalidIdException();
    }

    public ResponseEntity<?> put(Long id, Chapteree chapteree){
        if(this.existsById(id)){
            Chapteree updatedChap = this.update(id, chapteree);
            try {
                return ResponseEntity.ok().location(new URI(String.valueOf(updatedChap.getChaptereeId())))
                        .body(updatedChap);
            } catch (URISyntaxException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        else throw new InvalidIdException();
    }

    public ResponseEntity<?> getChaptereeCustomers(Long id){
        if(this.existsById(id)){
            try {
                return ResponseEntity.ok().location(new URI(String.valueOf(id)))
                        .body(this.myCustomers(id));
            } catch (URISyntaxException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        }
        else throw new InvalidIdException();
    }
    public List<List<String>> listChaptereeCustomers() {
        List<List<String>> retList = new ArrayList<>();
        List<Long> chaptereeIds;
        chaptereeIds = chaptereeCustomerRepository.findAll().stream().map(x -> x.getChaptereeId()).distinct().collect(Collectors.toList());
        for(Long i : chaptereeIds) {
            retList.add(this.myCustomers(i));
        }
        return retList;
    }
}