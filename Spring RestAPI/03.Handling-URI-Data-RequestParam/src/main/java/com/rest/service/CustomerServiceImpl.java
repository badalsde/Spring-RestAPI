package com.rest.service;

import com.rest.dto.*;
import com.rest.entity.FriendFamily;
import com.rest.entity.Plan;
import com.rest.exception.BankException;
import com.rest.mapper.CustomerMapper;
import com.rest.mapper.FriendFamilyMapper;
import com.rest.mapper.PlanMapper;
import com.rest.repository.CustomerRepository;
import com.rest.repository.PlanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rest.entity.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value="customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

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
    public String addCustomer(CustomerDTO customerDTO) throws BankException{
        if (customerRepository.findByPhoneNo(customerDTO.getPhoneNo())!= null) {
            throw new BankException("Customer with phone no " + customerDTO.getPhoneNo() + " already exists");
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

        return "Customer added with phone no " + customer.getPhoneNo()+ " successfully...";
    }


    @Override
    public List<CustomerDTO> getAllCustomer() throws BankException{
        List<Customer> customerList = (List<Customer>) customerRepository.findAll();
        if (customerList.isEmpty()) {
            throw new BankException("Customer not found");
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
    public CustomerDTO getCustomerByPhoneNo(long phoneNo) throws BankException {
        Customer customer = customerRepository.findByPhoneNo(phoneNo);
        if (customer == null) {
            throw new BankException("Customer not found with phone no " + phoneNo);
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
    public String updateCustomer(long phoneNo, CustomerDTO customerDTO) throws BankException {
        Customer customer = customerRepository.findByPhoneNo(phoneNo);
        if (customer == null) {
            throw new BankException("Customer not found with phone no " + phoneNo);
        }
        // Update customer's attributes
        customer = customerMapper.toEntity(customerDTO);

        // Update the current plan if provided in the DTO
        PlanDTO planDTO = customerDTO.getCurrentPlan();
        if (planDTO != null) {
            Plan plan = planRepository.findById(planDTO.getPlanId()).orElseThrow(() -> new BankException("Plan not found with ID " + planDTO.getPlanId()));
            customer.setCurrentPlan(plan);
        }

        // Update friend and family list
        List<FriendFamilyDTO> friendAndFamilyDTOList = customerDTO.getFriendAndFamily();
        if (friendAndFamilyDTOList != null) {
            List<FriendFamily> friendAndFamilyList = friendFamilyMapper.toEntity(friendAndFamilyDTOList);
            customer.setFriendAndFamily(friendAndFamilyList);
        }

        customerRepository.save(customer);

        return "Customer updated with phone no " + customer.getPhoneNo()+ " successfully...";
    }

    @Override
    public String deleteCustomer(long phoneNo) throws BankException {
        Customer customer = customerRepository.findByPhoneNo(phoneNo);
        if (customer == null) {
            throw new BankException("Customer not found with phone no " + phoneNo);
        }
        customerRepository.delete(customer);
        return "Customer deleted with phone no " + customer.getPhoneNo()+ " successfully...";
    }

}
