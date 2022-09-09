package com.agileactors.chapteree_app.service;

import com.agileactors.chapteree_app.exception.InvalidLevelException;
import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.repository.ChaptereeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ChaptereeService {

    @Autowired
    ChaptereeRepository chaptereeRepository;

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

    private void validateLevel(String lvl) {
        if(!EnumUtils.isValidEnum(Chapteree.Level.class, lvl)) throw new InvalidLevelException();
    }
}