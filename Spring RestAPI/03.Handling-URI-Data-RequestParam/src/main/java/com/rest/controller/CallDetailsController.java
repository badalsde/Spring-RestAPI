package com.rest.controller;

import com.rest.exception.BankException;
import com.rest.service.CallDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.rest.dto.CallDetailsDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/calldetails")
public class CallDetailsController {

    @Autowired
    private CallDetailsService callDetailsService;

    @RequestMapping(produces = "application/json")
    public List<CallDetailsDTO> fetchCallDetails(@RequestParam("calledBy") long calledBy, @RequestParam("calledOn") LocalDate calledOn) throws BankException {
        return callDetailsService.fetchCallDetails(calledBy, calledOn);
    }
}
