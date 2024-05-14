package com.rest.service;

import com.rest.dto.CustomerDTO;
import com.rest.exception.NoSuchCustomerException;

import java.util.List;

public interface CustomerService {
    public Long addCustomer(CustomerDTO customerDTO) throws NoSuchCustomerException;
    public List<CustomerDTO> getAllCustomer() throws NoSuchCustomerException;
    public CustomerDTO getCustomerByPhoneNo(long phoneNo) throws NoSuchCustomerException;
    public void updateCustomer(long phoneNo, CustomerDTO customerDTO) throws NoSuchCustomerException;
    public void deleteCustomer(long phoneNo) throws NoSuchCustomerException;
}
