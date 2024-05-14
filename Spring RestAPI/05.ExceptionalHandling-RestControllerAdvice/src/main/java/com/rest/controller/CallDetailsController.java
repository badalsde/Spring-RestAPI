package com.rest.controller;

import com.rest.exception.NoSuchCallDetailsException;
import com.rest.service.CallDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.rest.dto.CallDetailsDTO;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/calldetails")
public class CallDetailsController {

    @Autowired
    private CallDetailsService callDetailsService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CallDetailsDTO>> fetchCallDetails(@RequestParam("calledBy") long calledBy, @RequestParam("calledOn") LocalDate calledOn) throws NoSuchCallDetailsException {
        List<CallDetailsDTO> callDetailsDTO = callDetailsService.fetchCallDetails(calledBy, calledOn);
    return new ResponseEntity<>(callDetailsDTO, HttpStatus.OK);
    }
}
