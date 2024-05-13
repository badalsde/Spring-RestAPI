package com.rest.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
   @NotNull(message = "{customer.phone.must}")
   private long phoneNo;
   @NotNull(message = "{customer.name.must}")
   private String name;
   @NotNull(message = "{customer.email.must}")
   @Email(message = "{customer.email.invalid}")
   private String email;
   @Min(value = 18,message="{customer.age.invalid}")
   @Max(value=60,message="{customer.age.invalid}")
   private int age;
   private char gender;
   private List<FriendFamilyDTO> friendAndFamily;
   @NotNull(message = "{customer.password.must}")
   @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\\\S+$).{5,}$", message="{customer.password.invalid}")
   private String password;
   private String address;
   @NotNull(message="{customer.plan.must}")
   private PlanDTO currentPlan;

}
