package com.rest.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomController {

    @PostMapping
    public String addCustomer(){
        return "Customer added successfully";
    }

    @GetMapping
    public String fetchCustomer(){
        return "Fetch the customer details";
    }

    @PutMapping
    public String updateCustomer(){
        return "Updated the customer";
    }

    @DeleteMapping
    public String deleteCustomer(){
        return "deleted customer";
    }
}
