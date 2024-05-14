package com.rest.dto;

import com.rest.entity.Plan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanDTO {
    private Integer planId;
    private String planName;
    private Integer nationalRate;
    private Integer localRate;


}
