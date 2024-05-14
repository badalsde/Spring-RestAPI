package com.rest.repository;

import com.rest.entity.Plan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlanRepository extends CrudRepository<Plan, Integer> {
    public List<Plan> findByLocalRate(Integer localRate);
}
