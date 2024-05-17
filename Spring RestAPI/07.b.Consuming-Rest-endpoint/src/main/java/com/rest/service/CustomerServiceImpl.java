package com.rest.service;

import com.rest.dto.CustomerDTO;
import com.rest.dto.PlanDTO;
import com.rest.entity.Customer;
import com.rest.entity.Plan;
import com.rest.exception.NoSuchCustomerException;
import com.rest.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void addCustomer(CustomerDTO customerDTO, int planId) throws NoSuchCustomerException {
        if(customerRepository.findByPhoneNo(customerDTO.getPhoneNo())!=null) {
            throw new NoSuchCustomerException("Customer already exists");
        }
        Customer customer = new Customer();
        customer.setPhoneNo(customerDTO.getPhoneNo());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setAge(customerDTO.getAge());
        customer.setGender(customerDTO.getGender());
        customer.setPassword(customerDTO.getPassword());
        customer.setAddress(customerDTO.getAddress());

        WebClient client = WebClient.create();
        //post the request through client endpoint to plan DB
        String url = "http://localhost:8191/plans/{planId}";
        Plan responsePlan = client.get()
                .uri(url, planId)
                .retrieve()
                .bodyToMono(Plan.class)
                .block();

        if (responsePlan == null) {
            throw new NoSuchCustomerException("Plan not found");
        }

        customer.setCurrentPlan(responsePlan);
        customerRepository.save(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomer() throws NoSuchCustomerException {
        List<Customer> customerList = (List<Customer>) customerRepository.findAll();
        if (customerList.isEmpty()) {
            throw new NoSuchCustomerException("Customer not found");
        }
        WebClient client = WebClient.create();
        String url = "http://localhost:8191/plans";
        List<PlanDTO> plans = client.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(PlanDTO.class)
                .collectList()
                .block();

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

            PlanDTO matchedPlan = plans.stream()
                    .filter(plan -> plan.getPlanId() == customer.getCurrentPlan().getPlanId()) // Assuming Customer has a currentPlanId field
                    .findFirst()
                    .orElse(null);
            customerDTO.setCurrentPlan(matchedPlan);

            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }
}
