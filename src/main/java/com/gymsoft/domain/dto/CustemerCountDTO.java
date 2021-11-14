package com.gymsoft.domain.dto;

import lombok.Data;

@Data
public class CustemerCountDTO
{
    private Integer totalCustomers;
    private Integer activeCustomers;
    private Integer inActiveCustomers;
    private Integer aboutToDueCustomers;
}
