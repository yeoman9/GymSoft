package com.gymsoft.domain.dto;

import lombok.Data;

@Data
public class PaymentCountDTO
{
    private Integer todayCount;
    private Integer weeklyCount;
    private Integer monthlyCount;
    private Integer todayCollection;
    private Integer weeklyCollection;
    private Integer monthlyCollection;
}
