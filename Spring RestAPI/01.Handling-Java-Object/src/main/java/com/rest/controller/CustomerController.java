package com.rest.controller;

import com.rest.dto.CustomerDTO;
import com.rest.exception.BankException;
import com.rest.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping(produces = "application/json")
    public List<CustomerDTO> fetchCustomer() throws BankException {

        return customerService.getAllCustomer();
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customerDTO) throws BankException {
        String response = customerService.addCustomer(customerDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public String updateCustomer(){
        return "updated Customer details";
    }

    @DeleteMapping
    public String deleteCustomer(){
        return "deleted Customer successfully";
    }
}
