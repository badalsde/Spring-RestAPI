package com.rest.service;

import com.rest.dto.CustomerDTO;
import com.rest.exception.NoSuchCustomerException;

import java.util.List;

public interface CustomerService {
    public void addCustomer(CustomerDTO customerDTO, int planId) throws NoSuchCustomerException;
    public List<CustomerDTO> getAllCustomer() throws NoSuchCustomerException;
}
