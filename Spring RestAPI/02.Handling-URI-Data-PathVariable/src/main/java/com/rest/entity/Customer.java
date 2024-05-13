package com.rest.entity;

import com.rest.dto.FriendFamilyDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @Column(name = "phone_no")
    private long phoneNo;

    private String name;
    private String email;
    private int age;
    private char gender;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)  // Use CascadeType.ALL and orphanRemoval to properly handle relationships
    @JoinColumn(name = "customer_phone_no", referencedColumnName = "phone_no")  // Specify the join column and its reference column
    private List<FriendFamily> friendAndFamily = new ArrayList<>();  // Initialize the list to avoid NullPointerException

    private String password;
    private String address;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "plan_id")
    private Plan currentPlan;
}
