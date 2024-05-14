package com.rest.mapper;

import com.rest.dto.CallDetailsDTO;
import com.rest.entity.CallDetails;
import org.springframework.stereotype.Component;

@Component
public class CallDetailsMapper {

    public CallDetails toEntity(CallDetailsDTO callDetailsDTO){
        CallDetails callDetails = new CallDetails();
        callDetails.setCalledBy(callDetailsDTO.getCalledBy());
        callDetails.setCalledOn(callDetailsDTO.getCalledOn());
        callDetails.setCalledTo(callDetailsDTO.getCalledTo());
        callDetails.setDuration(callDetailsDTO.getDuration());
        return callDetails;
    }

    public CallDetailsDTO toDTO(CallDetails callDetails){
        CallDetailsDTO callDetailsDTO = new CallDetailsDTO();
        callDetailsDTO.setCalledBy(callDetails.getCalledBy());
        callDetailsDTO.setCalledOn(callDetails.getCalledOn());
        callDetailsDTO.setCalledTo(callDetails.getCalledTo());
        callDetailsDTO.setDuration(callDetails.getDuration());
        return callDetailsDTO;
    }
}
