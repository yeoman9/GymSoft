package com.gymsoft.domain.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class PaymentDTO
{
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date paymentFrom;
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date paymentTo;
    
    private String amount;
    private String months;
    private String customerId;
}
