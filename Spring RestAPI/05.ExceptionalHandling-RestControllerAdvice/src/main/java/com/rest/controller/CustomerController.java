package com.rest.controller;

import com.rest.dto.CustomerDTO;
import com.rest.exception.NoSuchCustomerException;
import com.rest.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private Environment environment;

    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping(value="/{phoneNo}")
    public ResponseEntity<CustomerDTO> fetchCustomer(@PathVariable("phoneNo") long phoneNo) throws NoSuchCustomerException {
        CustomerDTO customerDTO = customerService.getCustomerByPhoneNo(phoneNo);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CustomerDTO>> fetchCustomer() throws NoSuchCustomerException {
        List<CustomerDTO> customerDTOList = customerService.getAllCustomer();
        return new ResponseEntity<>(customerDTOList, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customerDTO) throws NoSuchCustomerException {
        Long phoneNo = customerService.addCustomer(customerDTO);
        String successMessage=environment.getProperty("API.INSERTED_SUCCESS")+ phoneNo;
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }

    @PutMapping(value ="/{phoneNo}", consumes = "application/json")
    public ResponseEntity<String> updateCustomer(@PathVariable("phoneNo") long phoneNo, @RequestBody CustomerDTO customerDTO) throws NoSuchCustomerException {
        customerService.updateCustomer(phoneNo, customerDTO);
        String successMessage= environment.getProperty("API.UPDATE_SUCCESS");
        return new ResponseEntity<>(successMessage,HttpStatus.OK);
    }

    @DeleteMapping(value = "/{phoneNo}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("phoneNo") long phoneNo) throws NoSuchCustomerException {
        customerService.deleteCustomer(phoneNo);
        String successMessage = environment.getProperty("API.DELETE_SUCCESS");
        return new ResponseEntity<>(successMessage,HttpStatus.OK);
    }
}
