package com.rest.service;

import com.rest.dto.PlanDTO;
import com.rest.exception.NoSuchPlanException;

public interface PlanService {

    public PlanDTO fetchPlan(Integer planId) throws NoSuchPlanException;
    public void createPlan(PlanDTO planDTO) throws NoSuchPlanException;
}
