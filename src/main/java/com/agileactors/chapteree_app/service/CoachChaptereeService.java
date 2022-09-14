package com.agileactors.chapteree_app.service;

import com.agileactors.chapteree_app.exception.InvalidIdException;
import com.agileactors.chapteree_app.exception.NoCoachException;
import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.model.CoachChapteree;
import com.agileactors.chapteree_app.model.Ids;
import com.agileactors.chapteree_app.repository.ChaptereeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CoachChaptereeService {
    @Autowired
    ChaptereeRepository chaptereeRepository;

    public CoachChaptereeService(ChaptereeRepository chaptereeRepository) {
        this.chaptereeRepository = chaptereeRepository;
    }
    public List<CoachChapteree> findAll() {
        List<CoachChapteree> returnList = new ArrayList<>();
        List<Chapteree> chapterees = chaptereeRepository.findAll();
       for(Chapteree chap: chapterees){
           if(chap.getCoachId()!=null)
           returnList.add(getCoach(chap.getChaptereeId()));
       }
    return returnList;
    }

    public boolean existsById(long id) { return this.chaptereeRepository.existsById(id); }

    public Chapteree getOne(Long id) { return this.chaptereeRepository.getOne(id);}
    public Chapteree findById(Long id) { return this.chaptereeRepository.findById(id).get();}

    public CoachChapteree getCoach(Long id) {
        Chapteree chap = this.getOne(id);
        if(chap.getCoachId()==null) throw new NoCoachException();
        Chapteree coach = this.getOne(chap.getCoachId());
        return new CoachChapteree(coach, chap);
    }

    public Chapteree update(Long id, Chapteree chapteree) {
        Chapteree existingChapteree = getOne(id);
        BeanUtils.copyProperties(chapteree, existingChapteree, "chaptereeId");
        return chaptereeRepository.save(existingChapteree);
    }


    public CoachChapteree save(Chapteree chapteree, Long coachId) {
        chapteree.setCoachId(coachId);
        update(chapteree.getChaptereeId(), chapteree);
        if(coachId==null) return new CoachChapteree(null,chapteree);
        return new CoachChapteree(getOne(coachId),chapteree);
    }

    public List<Chapteree> findAllChapterees(Long id) {
        return new ArrayList<>(chaptereeRepository.findAll().stream()
                .filter(chapteree -> chapteree.getCoachId()==id)
                .collect(Collectors.toList()));
    }

    public ResponseEntity<?> getById(Long id) {
        if(this.existsById(id)){
            CoachChapteree coachChapteree = this.getCoach(id);
            try {
                return ResponseEntity.ok().location(new URI(String.valueOf(coachChapteree.getChaptereeId())))
                        .body(coachChapteree);
            } catch (URISyntaxException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        }
        else throw new InvalidIdException();
    }

    public ResponseEntity<?> deleteById(Long id) {
        if(this.existsById(id)){
            this.save(this.getOne(id),null);
            return ResponseEntity.ok().build();
        }
        else throw new InvalidIdException();
    }

    public ResponseEntity<?> put(Long id, Ids ids) {
        if(this.existsById(id)){
            CoachChapteree updatedChap = this.save(this.getOne(id),
                    ids.getCoachId());
            try {
                return ResponseEntity.ok().location(new URI(String.valueOf(updatedChap.getChaptereeId())))
                        .body(updatedChap);
            } catch (URISyntaxException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        else throw new InvalidIdException();
    }

    public ResponseEntity<?> create(Ids ids) {
        Long coachId= ids.getCoachId();
        Long chaptereeId = ids.getChaptereeId();
        if (this.existsById(chaptereeId) && this.existsById(coachId)) {
            CoachChapteree newCoachChap = this.save(this.getOne(chaptereeId), coachId);
            try {
                return ResponseEntity.created(new URI(String.valueOf(newCoachChap.getChaptereeId())))
                        .body(newCoachChap);
            } catch (URISyntaxException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        else throw new InvalidIdException();
    }

    public Chapteree saveAndFlush(Chapteree chapteree) { return this.chaptereeRepository.save(chapteree); }

    public void delete(Long id) { this.chaptereeRepository.deleteById(id); }
}
