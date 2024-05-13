package com.rest.repository;

import com.rest.entity.CallDetails;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface CallDetailsRepository extends CrudRepository<CallDetails, Long> {
    List<CallDetails> findByCalledByAndCalledOn(Long calledBy, LocalDate calledOn);
}
