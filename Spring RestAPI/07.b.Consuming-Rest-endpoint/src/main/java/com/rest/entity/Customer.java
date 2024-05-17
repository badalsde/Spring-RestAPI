package com.rest.entity;

import com.rest.dto.PlanDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    private long phoneNo;
    private String name;
    private String email;
    private int age;
    private char gender;
    private String password;
    private String address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    private Plan currentPlan;
}
