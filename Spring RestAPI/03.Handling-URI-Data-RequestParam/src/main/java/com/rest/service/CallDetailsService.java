package com.rest.service;

import com.rest.mapper.CallDetailsMapper;
import com.rest.entity.CallDetails;
import com.rest.repository.CallDetailsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rest.exception.BankException;
import com.rest.dto.CallDetailsDTO;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

@Service("callDetailsService")
@Transactional
public class CallDetailsService {

    @Autowired
    private CallDetailsRepository callDetailsRepository;

    @Autowired
    private CallDetailsMapper callDetailsMapper;

    public List<CallDetailsDTO> fetchCallDetails(long calledBy, LocalDate calledOn) throws BankException {
        List<CallDetails> callDetailsList = callDetailsRepository.findByCalledByAndCalledOn(calledBy, calledOn);
        if (callDetailsList.isEmpty()) {
            throw new BankException("No call details found");
        }
        List<CallDetailsDTO> callDetailsDTOS = new ArrayList<>();
        for(CallDetails callDetails : callDetailsList){
            CallDetailsDTO callDetailsDTO = new CallDetailsDTO();
            callDetailsDTOS.add(callDetailsMapper.toDTO(callDetails));
        }
        return callDetailsDTOS;
    }
}
