package com.rest.service;

import com.rest.dto.CustomerDTO;
import com.rest.dto.FriendFamilyDTO;
import com.rest.dto.PlanDTO;
import com.rest.entity.FriendFamily;
import com.rest.entity.Plan;
import com.rest.exception.BankException;
import com.rest.repository.CustomerRepository;
import com.rest.repository.PlanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rest.entity.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service(value="customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PlanRepository planRepository;

    @Override
    public String addCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setPhoneNo(customerDTO.getPhoneNo());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setAge(customerDTO.getAge());
        customer.setGender(customerDTO.getGender());
        customer.setPassword(customerDTO.getPassword());
        customer.setAddress(customerDTO.getAddress());

        PlanDTO planDTO = customerDTO.getCurrentPlan();
        Plan plan = planRepository.findById(planDTO.getPlanId()).orElse(null);
        if (plan == null) {
            plan = new Plan();
            plan.setPlanId(planDTO.getPlanId());
            plan.setPlanName(planDTO.getPlanName());
            plan.setNationalRate(planDTO.getNationalRate());
            plan.setLocalRate(planDTO.getLocalRate());
            plan = planRepository.save(plan);
        }
        customer.setCurrentPlan(plan);


        List<FriendFamily> friendAndFamily = new ArrayList<>();
        List<FriendFamilyDTO> friendAndFamilyDTO = customerDTO.getFriendAndFamily();
        for(FriendFamilyDTO friendFamilyDTO : friendAndFamilyDTO){
            FriendFamily friendFamily = new FriendFamily();
            friendFamily.setPhoneNo(friendFamilyDTO.getPhoneNo());
            friendFamily.setFriendAndFamily(friendFamilyDTO.getFriendAndFamily());
            friendAndFamily.add(friendFamily);
        }
        customer.setFriendAndFamily(friendAndFamily);
    customerRepository.save(customer);
        return "Customer added with phone no " + customer.getPhoneNo()+ " successfully...";
    }


    @Override
    public List<CustomerDTO> getAllCustomer(){
        List<Customer> customerList = (List<Customer>) customerRepository.findAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for(Customer customer: customerList){
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setPhoneNo(customer.getPhoneNo());
            customerDTO.setName(customer.getName());
            customerDTO.setEmail(customer.getEmail());
            customerDTO.setAge(customer.getAge());
            customerDTO.setGender(customer.getGender());
            customerDTO.setPassword(customer.getPassword());
            customerDTO.setAddress(customer.getAddress());

            PlanDTO planDTO = new PlanDTO();
            Plan plan= customer.getCurrentPlan();
            if (plan != null) { // Add null check here
                planDTO.setPlanId(plan.getPlanId());
                planDTO.setPlanName(plan.getPlanName());
                planDTO.setNationalRate(plan.getNationalRate());
                planDTO.setLocalRate(plan.getLocalRate());
                customerDTO.setCurrentPlan(planDTO);
            }

            customerDTO.setFriendAndFamily(convertFriendFamilyListToDTO(customer.getFriendAndFamily()));
            customerDTO.getFriendAndFamily();

            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }
    private List<FriendFamilyDTO> convertFriendFamilyListToDTO(List<FriendFamily> friendFamilyList) {
        if (friendFamilyList == null) {
            return null;
        }
        return friendFamilyList.stream()
                .map(this::convertFriendFamilyToDTO)
                .collect(Collectors.toList());
    }

    private FriendFamilyDTO convertFriendFamilyToDTO(FriendFamily friendFamily) {
        FriendFamilyDTO friendFamilyDTO = new FriendFamilyDTO();
        friendFamilyDTO.setPhoneNo(friendFamily.getPhoneNo());
        friendFamilyDTO.setFriendAndFamily(friendFamily.getFriendAndFamily());
        return friendFamilyDTO;
    }

}
