package com.gymsoft.domain.dto;

import lombok.Data;

@Data
public class DashboardDTO
{
    private Integer totalCustomers;
    private Integer activeCustomers;
    private Integer inActiveCustomers;
}
