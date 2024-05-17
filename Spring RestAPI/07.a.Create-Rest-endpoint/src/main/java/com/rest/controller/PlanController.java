package com.rest.controller;

import com.rest.dto.PlanDTO;
import com.rest.exception.NoSuchPlanException;
import com.rest.service.PlanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class PlanController {

    @Autowired
    private PlanServiceImpl planService;

    @GetMapping()
    public ResponseEntity<List<PlanDTO>> fetchAllPlans() throws NoSuchPlanException {
        List<PlanDTO> result = planService.fetchPlan();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{planId}")
    public ResponseEntity<PlanDTO> fetchPlans(@PathVariable("planId") Integer planId) throws NoSuchPlanException {
        PlanDTO planDTO = planService.fetchPlan(planId);
        return new ResponseEntity<>(planDTO, HttpStatus.OK);
    }

    @PostMapping(consumes="application/json")
    public ResponseEntity<String> createPlan(@RequestBody PlanDTO planDTO) throws NoSuchPlanException {
        planService.createPlan(planDTO);
        return new ResponseEntity<>("Plan created successfully", HttpStatus.CREATED);
    }
}
