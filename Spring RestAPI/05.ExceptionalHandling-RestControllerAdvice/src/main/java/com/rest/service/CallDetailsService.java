package com.rest.service;

import com.rest.exception.NoSuchCallDetailsException;
import com.rest.mapper.CallDetailsMapper;
import com.rest.entity.CallDetails;
import com.rest.repository.CallDetailsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.rest.dto.CallDetailsDTO;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

@Service("callDetailsService")
@Transactional
public class CallDetailsService {

    @Autowired
    private Environment environment;
    @Autowired
    private CallDetailsRepository callDetailsRepository;

    @Autowired
    private CallDetailsMapper callDetailsMapper;

    public List<CallDetailsDTO> fetchCallDetails(long calledBy, LocalDate calledOn) throws NoSuchCallDetailsException {
        List<CallDetails> callDetailsList = callDetailsRepository.findByCalledByAndCalledOn(calledBy, calledOn);
        if (callDetailsList.isEmpty()) {
            throw new NoSuchCallDetailsException(environment.getProperty("Service.CALL_DETAILS_NOT_FOUND"));
        }
        List<CallDetailsDTO> callDetailsDTOS = new ArrayList<>();
        for(CallDetails callDetails : callDetailsList){
            callDetailsDTOS.add(callDetailsMapper.toDTO(callDetails));
        }
        return callDetailsDTOS;
    }
}
