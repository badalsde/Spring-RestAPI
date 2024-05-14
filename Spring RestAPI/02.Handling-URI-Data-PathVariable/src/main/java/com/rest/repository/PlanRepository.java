package com.rest.repository;

import com.rest.entity.Plan;
import org.springframework.data.repository.CrudRepository;

public interface PlanRepository extends CrudRepository<Plan, Integer> {
}
