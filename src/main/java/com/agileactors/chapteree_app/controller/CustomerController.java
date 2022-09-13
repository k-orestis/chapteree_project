package com.agileactors.chapteree_app.controller;

import com.agileactors.chapteree_app.model.Customer;
import com.agileactors.chapteree_app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("api/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> list(){
        return customerService.findAll();
    }

    @GetMapping
    @RequestMapping(value = "{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
       return customerService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Customer customer){
        return customerService.create(customer);
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id){
        return customerService.delete(id);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Customer customer){
        return customerService.put(id, customer);
    }
}
