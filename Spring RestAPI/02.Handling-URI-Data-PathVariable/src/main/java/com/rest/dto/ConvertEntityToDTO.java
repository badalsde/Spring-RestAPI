package com.rest.dto;

import com.rest.entity.Customer;
import com.rest.entity.FriendFamily;
import com.rest.entity.Plan;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConvertEntityToDTO {

    public CustomerDTO mapCustomerToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setPhoneNo(customer.getPhoneNo());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setAge(customer.getAge());
        customerDTO.setGender(customer.getGender());
        customerDTO.setPassword(customer.getPassword());
        customerDTO.setAddress(customer.getAddress());
        return customerDTO;
    }

    public PlanDTO mapPlanToPlanDTO(Plan plan) {
        PlanDTO planDTO = new PlanDTO();
        planDTO.setPlanId(plan.getPlanId());
        planDTO.setPlanName(plan.getPlanName());
        planDTO.setNationalRate(plan.getNationalRate());
        planDTO.setLocalRate(plan.getLocalRate());
        return planDTO;
    }

    public List<FriendFamilyDTO> convertFriendFamilyListToDTO(List<FriendFamily> friendFamilyList) {
        if (friendFamilyList == null) {
            return Collections.emptyList();
        }
        return friendFamilyList.stream()
                .map(this::convertFriendFamilyToDTO)
                .collect(Collectors.toList());
    }
    public FriendFamilyDTO convertFriendFamilyToDTO(FriendFamily friendFamily) {
        FriendFamilyDTO friendFamilyDTO = new FriendFamilyDTO();
        friendFamilyDTO.setPhoneNo(friendFamily.getPhoneNo());
        friendFamilyDTO.setFriendAndFamily(friendFamily.getFriendAndFamily());
        return friendFamilyDTO;
    }


}
