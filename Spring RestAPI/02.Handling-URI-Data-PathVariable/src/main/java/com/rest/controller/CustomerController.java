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

    @GetMapping(value="/{phoneNo}")
    public CustomerDTO fetchCustomer(@PathVariable("phoneNo") long phoneNo) throws BankException {
        return customerService.getCustomerByPhoneNo(phoneNo);
    }

    @GetMapping(produces = "application/json")
    public List<CustomerDTO> fetchCustomer() throws BankException {

        return customerService.getAllCustomer();
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customerDTO) throws BankException {
        String response = customerService.addCustomer(customerDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value ="/{phoneNo}", consumes = "application/json")
    public String updateCustomer(@PathVariable("phoneNo") long phoneNo, @RequestBody CustomerDTO customerDTO) throws BankException {
        return customerService.updateCustomer(phoneNo, customerDTO);
    }

    @DeleteMapping(value = "/{phoneNo}")
    public String deleteCustomer(@PathVariable("phoneNo") long phoneNo) throws BankException {
        return customerService.deleteCustomer(phoneNo);
    }
}
