package com.rest.service;

import com.rest.dto.PlanDTO;
import com.rest.entity.Plan;
import com.rest.exception.NoSuchPlanException;
import com.rest.repository.PlanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.rest.mapper.PlanMapper;

@Service("planService")
@Transactional
public class PlanService {
    @Autowired
    private Environment environment;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private PlanMapper planMapper;

    public List<PlanDTO> fetchPlans() throws NoSuchPlanException {
        List<Plan> plans = (List<Plan>) planRepository.findAll();
        if(plans.isEmpty()){
            throw new NoSuchPlanException(environment.getProperty("Service.PLANS_NOT_FOUND"));
        }
        List<PlanDTO> planDTOS = new ArrayList<>();
        for(Plan plan: plans){
            planDTOS.add(planMapper.toDTO(plan));
        }
        return planDTOS;
    }

    public List<PlanDTO> plansLocalRate(List localRates) throws NoSuchPlanException{
        List<PlanDTO> planDTOS = new ArrayList<>();
        Iterator it = localRates.iterator();
        while (it.hasNext()){
            int localRate = Integer.parseInt((String) it.next());
            List<Plan> plans = (List<Plan>) planRepository.findByLocalRate(localRate);
            for(Plan plan: plans){
                planDTOS.add(planMapper.toDTO(plan));
            }
        }
        return planDTOS;
    }
}
