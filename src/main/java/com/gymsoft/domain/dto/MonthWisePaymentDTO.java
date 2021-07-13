package com.gymsoft.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

@AllArgsConstructor

public class MonthWisePaymentDTO
{
    private String month;
    private Long total;
    
}
