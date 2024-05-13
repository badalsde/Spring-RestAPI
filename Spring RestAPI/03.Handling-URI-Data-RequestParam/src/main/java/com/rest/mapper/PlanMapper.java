package com.rest.mapper;

import com.rest.dto.PlanDTO;
import com.rest.entity.Plan;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {

    public Plan toEntity(PlanDTO planDTO){
        Plan plan = new Plan();
        plan.setPlanId(planDTO.getPlanId());
        plan.setPlanName(planDTO.getPlanName());
        plan.setNationalRate(planDTO.getNationalRate());
        plan.setLocalRate(planDTO.getLocalRate());
        return plan;
    }

    public PlanDTO toDTO(Plan plan){
        PlanDTO planDTO = new PlanDTO();
        planDTO.setPlanId(plan.getPlanId());
        planDTO.setPlanName(plan.getPlanName());
        planDTO.setNationalRate(plan.getNationalRate());
        planDTO.setLocalRate(plan.getLocalRate());
        return planDTO;
    }
}
