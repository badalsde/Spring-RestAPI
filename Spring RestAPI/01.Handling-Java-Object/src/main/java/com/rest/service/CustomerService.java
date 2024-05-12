package com.rest.service;

import com.rest.dto.CustomerDTO;
import com.rest.exception.BankException;

import java.util.List;

public interface CustomerService {
    public String addCustomer(CustomerDTO customerDTO);
    public List<CustomerDTO> getAllCustomer();
}
