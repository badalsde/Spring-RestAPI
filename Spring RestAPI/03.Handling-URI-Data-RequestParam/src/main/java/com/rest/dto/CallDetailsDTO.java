package com.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallDetailsDTO {
    private long calledBy;
    private long calledTo;
    private LocalDate calledOn;
    private int duration;
}
