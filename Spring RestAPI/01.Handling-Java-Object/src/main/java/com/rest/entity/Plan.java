package com.rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plan {
    @Id
    private Integer planId;
    private String planName;
    private Integer nationalRate;
    private Integer localRate;
}
