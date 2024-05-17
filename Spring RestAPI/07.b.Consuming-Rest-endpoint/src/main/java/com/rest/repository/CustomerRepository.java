package com.rest.repository;

import com.rest.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    public Customer findByPhoneNo(long phoneNo);
}
