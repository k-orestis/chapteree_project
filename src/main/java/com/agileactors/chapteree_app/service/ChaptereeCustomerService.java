package com.agileactors.chapteree_app.service;

import com.agileactors.chapteree_app.exception.InvalidIdException;
import com.agileactors.chapteree_app.model.*;
import com.agileactors.chapteree_app.repository.ChaptereeCustomerRepository;
import com.agileactors.chapteree_app.repository.ChaptereeRepository;
import com.agileactors.chapteree_app.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class ChaptereeCustomerService {
    @Autowired
    ChaptereeCustomerRepository chaptereeCustomerRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ChaptereeRepository chaptereeRepository;

    public ChaptereeCustomerService(ChaptereeCustomerRepository chaptereeCustomerRepository,
                                    CustomerRepository customerRepository,
                                    ChaptereeRepository chaptereeRepository) {
        this.chaptereeCustomerRepository = chaptereeCustomerRepository;
        this.customerRepository = customerRepository;
        this.chaptereeRepository = chaptereeRepository;
    }


    public ResponseEntity<?> create(CustomerIds customerIds) {
        if (this.existsById(customerIds)) {
            ChaptereeCustomer newChapCust = this.save(customerIds);
            try {
                return ResponseEntity.created(new URI(String.valueOf(newChapCust.getChaptereeId())))
                        .body(newChapCust);
            } catch (URISyntaxException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        else throw new InvalidIdException();
    }

    public ChaptereeCustomer save(CustomerIds customerIds) {
        return chaptereeCustomerRepository.save(new ChaptereeCustomer(customerIds.getChaptereeId(),
                customerIds.getCustomerId()));
    }

    public boolean existsById(CustomerIds customerIds) {
        return this.customerRepository.existsById(customerIds.getCustomerId()) &&
                this.chaptereeRepository.existsById(customerIds.getChaptereeId());
    }
    public ResponseEntity<?> deleteById(Long id, CustomerIds customerIds) {
        if(this.existsById(customerIds)){
            this.delete(customerIds);
            return ResponseEntity.ok().build();
        }
        else throw new InvalidIdException();
    }

    public void delete(CustomerIds customerIds) {
        this.chaptereeCustomerRepository.deleteById(customerIds);
    }

}
