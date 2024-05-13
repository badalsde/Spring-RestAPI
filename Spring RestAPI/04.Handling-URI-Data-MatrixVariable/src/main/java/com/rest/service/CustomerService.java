package com.rest.service;

import com.rest.dto.CustomerDTO;
import com.rest.exception.BankException;

import java.util.List;

public interface CustomerService {
    public String addCustomer(CustomerDTO customerDTO) throws BankException;
    public List<CustomerDTO> getAllCustomer() throws BankException;
    public CustomerDTO getCustomerByPhoneNo(long phoneNo) throws BankException;
    public String updateCustomer(long phoneNo, CustomerDTO customerDTO) throws BankException;
    public String deleteCustomer(long phoneNo) throws BankException;
}
