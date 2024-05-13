package com.rest.service;

import com.rest.dto.*;
import com.rest.entity.FriendFamily;
import com.rest.entity.Plan;
import com.rest.exception.NoSuchCustomerException;
import com.rest.mapper.CustomerMapper;
import com.rest.mapper.FriendFamilyMapper;
import com.rest.mapper.PlanMapper;
import com.rest.repository.CustomerRepository;
import com.rest.repository.PlanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.rest.entity.Customer;

import java.util.ArrayList;
import java.util.List;

@Service(value="customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private Environment environment;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private PlanMapper planMapper;
    @Autowired
    private FriendFamilyMapper friendFamilyMapper;

    @Override
    public Long addCustomer(CustomerDTO customerDTO) throws NoSuchCustomerException{
        if (customerRepository.findByPhoneNo(customerDTO.getPhoneNo())!= null) {
            throw new NoSuchCustomerException(environment.getProperty("Service.CUSTOMER_ALREADY_AVAILABLE") +customerDTO.getPhoneNo());
        }
        Customer customer = customerMapper.toEntity(customerDTO);
        PlanDTO planDTO = customerDTO.getCurrentPlan();
        Plan plan = planRepository.findById(planDTO.getPlanId()).orElse(null);
        if (plan == null) {
            plan = planMapper.toEntity(planDTO);
            planRepository.save(plan);
        }
        customer.setCurrentPlan(plan);

        List<FriendFamily> friendFamilyList = friendFamilyMapper.toEntity(customerDTO.getFriendAndFamily());
        customer.setFriendAndFamily(friendFamilyList);
        customerRepository.save(customer);
        return customer.getPhoneNo();
    }


    @Override
    public List<CustomerDTO> getAllCustomer() throws NoSuchCustomerException{
        List<Customer> customerList = (List<Customer>) customerRepository.findAll();
        if (customerList.isEmpty()) {
            throw new NoSuchCustomerException(environment.getProperty("Service.CUSTOMERS_NOT_FOUND"));
        }
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for(Customer customer: customerList){
            CustomerDTO customerDTO = customerMapper.toDTO(customer);

            Plan plan= customer.getCurrentPlan();
            if (plan != null) { // Add null check here
                PlanDTO planDTO = planMapper.toDTO(plan);
                customerDTO.setCurrentPlan(planDTO);
            }
            customerDTO.setFriendAndFamily(friendFamilyMapper.ToDTOList(customer.getFriendAndFamily()));
            customerDTO.getFriendAndFamily();

            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @Override
    public CustomerDTO getCustomerByPhoneNo(long phoneNo) throws NoSuchCustomerException {
        Customer customer = customerRepository.findByPhoneNo(phoneNo);
        if (customer == null) {
            throw new NoSuchCustomerException(environment.getProperty("Service.CUSTOMER_NOT_FOUND")+ phoneNo);
        }
        CustomerDTO customerDTO = customerMapper.toDTO(customer);
        Plan plan= customer.getCurrentPlan();
        if (plan!= null) { // Add null check here
            PlanDTO planDTO = planMapper.toDTO(plan);
            customerDTO.setCurrentPlan(planDTO);
        }
        customerDTO.setFriendAndFamily(friendFamilyMapper.ToDTOList(customer.getFriendAndFamily()));
        customerDTO.getFriendAndFamily();
        return customerDTO;
    }

    @Override
    public void updateCustomer(long phoneNo, CustomerDTO customerDTO) throws NoSuchCustomerException{
        Customer customer = customerRepository.findByPhoneNo(phoneNo);
        if (customer == null) {
            throw new NoSuchCustomerException(environment.getProperty("Service.CUSTOMER_NOT_FOUND")+ phoneNo);
        }
        // Update customer's attributes
        customer = customerMapper.toEntity(customerDTO);

        // Update the current plan if provided in the DTO
        PlanDTO planDTO = customerDTO.getCurrentPlan();
        if (planDTO != null) {
            Plan plan = planRepository.findById(planDTO.getPlanId()).orElseThrow(() -> new NoSuchCustomerException(environment.getProperty("Service.PLAN_NOT_FOUND") + planDTO.getPlanId()));
            customer.setCurrentPlan(plan);
        }

        // Update friend and family list
        List<FriendFamilyDTO> friendAndFamilyDTOList = customerDTO.getFriendAndFamily();
        if (friendAndFamilyDTOList != null) {
            List<FriendFamily> friendAndFamilyList = friendFamilyMapper.toEntity(friendAndFamilyDTOList);
            customer.setFriendAndFamily(friendAndFamilyList);
        }

        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(long phoneNo) throws NoSuchCustomerException {
        Customer customer = customerRepository.findByPhoneNo(phoneNo);
        if (customer == null) {
            throw new NoSuchCustomerException(environment.getProperty("Service.CUSTOMER_NOT_FOUND"));
        }
        customerRepository.delete(customer);
    }

}
