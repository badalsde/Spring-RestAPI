package com.rest.dto;

import com.rest.entity.Customer;
import com.rest.entity.FriendFamily;
import com.rest.entity.Plan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConvertDTOToEntity {

    public Customer mapCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setPhoneNo(customerDTO.getPhoneNo());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setAge(customerDTO.getAge());
        customer.setGender(customerDTO.getGender());
        customer.setPassword(customerDTO.getPassword());
        customer.setAddress(customerDTO.getAddress());
        return customer;
    }

    public Plan createPlanFromDTO(PlanDTO planDTO) {
        Plan plan = new Plan();
        plan.setPlanId(planDTO.getPlanId());
        plan.setPlanName(planDTO.getPlanName());
        plan.setNationalRate(planDTO.getNationalRate());
        plan.setLocalRate(planDTO.getLocalRate());
        return plan;
    }

    public List<FriendFamily> mapFriendFamilyDTOList(List<FriendFamilyDTO> friendFamilyDTOList) {
        List<FriendFamily> friendAndFamily = new ArrayList<>();
        for (FriendFamilyDTO friendFamilyDTO : friendFamilyDTOList) {
            FriendFamily friendFamily = new FriendFamily();
            friendFamily.setPhoneNo(friendFamilyDTO.getPhoneNo());
            friendFamily.setFriendAndFamily(friendFamilyDTO.getFriendAndFamily());
            friendAndFamily.add(friendFamily);
        }
        return friendAndFamily;
    }
}
