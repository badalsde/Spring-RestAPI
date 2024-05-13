package com.rest.controller;

import com.rest.dto.PlanDTO;
import com.rest.exception.BankException;
import com.rest.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/plans")
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping(produces = "application/json")
    public List<PlanDTO> fetchPlan() throws BankException {
        return planService.fetchPlans();
    }

    //URL for testing - http://localhost:8292/plans/query;localRate=3,5/plan
    @GetMapping(value="/{query}/plan", produces = {"application/json", "application/xml", "application/xml"})
    public List<PlanDTO> plansLocalRate(@MatrixVariable(pathVar = "query") Map<String,List<Integer>> map) throws BankException {
        Set<String> keysLocalRates = map.keySet();
        List localRates = new ArrayList<>();
        for (String key : keysLocalRates) {
            localRates.addAll(map.get(key));
        }
        return planService.plansLocalRate(localRates);
    }
}
