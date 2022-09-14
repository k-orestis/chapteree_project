package com.agileactors.chapteree_app.service;

import com.agileactors.chapteree_app.exception.InvalidIdException;
import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.model.Customer;
import com.agileactors.chapteree_app.repository.ChaptereeCustomerRepository;
import com.agileactors.chapteree_app.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private ChaptereeCustomerRepository chaptereeCustomerRepository;

    public CustomerService(CustomerRepository customerRepository) { this.customerRepository = customerRepository; }

    //findAll
    public List<Customer> findAll() { return this.customerRepository.findAll(); }

    //existsById
    public boolean existsById(long id) { return this.customerRepository.existsById(id); }

    //getOne
    public Customer getOne(Long id) { return this.customerRepository.getOne(id); }

    //saveAndFlush
    public Customer saveAndFlush(Customer customer) { return this.customerRepository.saveAndFlush(customer); }

    //save
    public Customer save(Customer customer) {
        return this.customerRepository.save(customer);
    }

    //deleteById
    public void deleteById(Long id) { this.customerRepository.deleteById(id); }

    //update

    public Customer update(Long id, Customer customer) {
        Customer existingCustomer = getOne(id);
        BeanUtils.copyProperties(customer, existingCustomer, "customerId");
        return save(existingCustomer);
    }

    public List<String> myChapterees(Long id) {
        List<String> strL = new ArrayList<>();
        strL.add("Customer: "+id.toString()+","+this.getOne(id).getFirstName()+","+this.getOne(id).getLastName());
        for (String str : customerRepository.myChapterees(id)) {
            strL.add("Chapteree: "+str);
        }
        return strL;
    }

    //Request functions
    public ResponseEntity<?> get(Long id) {
        if (this.existsById(id)) {
            Customer customer = this.getOne(id);
            try {
                return ResponseEntity.ok().location(new URI(String.valueOf(customer.getCustomerId())))
                        .body(customer);
            } catch (URISyntaxException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        } else throw new InvalidIdException();
    }

    public ResponseEntity<?> create(Customer customer) {
        Customer newCustomer = this.save(customer);
        try{
            return ResponseEntity.created(new URI(String.valueOf(newCustomer.getCustomerId())))
                    .body(newCustomer);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<?> delete(@PathVariable Long id){
        if(this.existsById(id)){
            this.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else throw new InvalidIdException();
    }

    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Customer customer){
        if(this.existsById(id)){
            Customer newCustomer = this.update(id, customer);
            try {
                return ResponseEntity.ok().location(new URI(String.valueOf(newCustomer.getCustomerId())))
                        .body(newCustomer);
            } catch (URISyntaxException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        else throw new InvalidIdException();
    }

    public ResponseEntity<?> getCustomerChapterees(Long id) {
        if (this.existsById(id)) {
            Customer customer = this.getOne(id);
            try {
                return ResponseEntity.ok().location(new URI(String.valueOf(customer.getCustomerId())))
                        .body(this.myChapterees(id));
            } catch (URISyntaxException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        } else throw new InvalidIdException();
    }

    public List<List<String>> listCustomerChapterees() {
        List<List<String>> retList = new ArrayList<>();
        List<Long> customerIds = new ArrayList<>();
        customerIds = chaptereeCustomerRepository.findAll().stream().map(x -> x.getCustomerId())
                .distinct().collect(Collectors.toList());
        for(Long i : customerIds) {
            retList.add(this.myChapterees(i));
        }
        return retList;
    }

}

