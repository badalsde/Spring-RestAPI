package com.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
   private long phoneNo;
   private String name;
   private String email;
   private int age;
   private char gender;
   private String password;
   private String address;
   private PlanDTO currentPlan;
}
