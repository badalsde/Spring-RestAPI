package com.rest.service;

import com.rest.dto.PlanDTO;
import com.rest.entity.Plan;
import com.rest.exception.NoSuchPlanException;
import com.rest.repository.PlanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("planService")
@Transactional
public class PlanServiceImpl implements PlanService{

    @Autowired
    private PlanRepository planRepository;

    @Override
    public PlanDTO fetchPlan(Integer planId) throws NoSuchPlanException {
        Plan plan = planRepository.findPlanById(planId);
        if(plan == null){
            throw new NoSuchPlanException("No plan details found");
        }
        PlanDTO planDTO = new PlanDTO();
        planDTO.setPlanId(plan.getPlanId());
        planDTO.setPlanName(plan.getPlanName());
        planDTO.setNationalRate(plan.getNationalRate());
        planDTO.setLocalRate(plan.getLocalRate());
        return planDTO;
    }

    @Override
    public void createPlan(PlanDTO planDTO) throws NoSuchPlanException {
        Plan plan = new Plan();
        if(planRepository.findPlanById(planDTO.getPlanId())!=null) {
            throw new NoSuchPlanException("Plan already exists");
        }
        plan.setPlanId(planDTO.getPlanId());
        plan.setPlanName(planDTO.getPlanName());
        plan.setNationalRate(planDTO.getNationalRate());
        plan.setLocalRate(planDTO.getLocalRate());
        planRepository.save(plan);
    }
}
