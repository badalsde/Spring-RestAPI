package com.rest.controller;

import com.rest.dto.CustomerDTO;
import com.rest.exception.NoSuchCustomerException;
import com.rest.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping(value="/{planId}",consumes = "application/json")
    public String addCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable("planId") Integer planId) throws NoSuchCustomerException {
        customerService.addCustomer(customerDTO, planId);
        return "Customer added successfully with phoneNo: " + customerDTO.getPhoneNo();
    }

    @GetMapping(value = "/customers", produces = "application/json")
    public ResponseEntity<List<CustomerDTO>> fetchCustomer() throws NoSuchCustomerException {

        List<CustomerDTO> customers = customerService.getAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
