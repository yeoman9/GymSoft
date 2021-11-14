package com.gymsoft.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymsoft.domain.dto.CustemerCountDTO;
import com.gymsoft.domain.dto.MonthWisePaymentDTO;
import com.gymsoft.domain.dto.PaymentCountDTO;
import com.gymsoft.domain.service.CustomerService;
import com.gymsoft.domain.service.PaymentService;

@RestController
@RequestMapping( "/apis/v1/dashboard" )
@CrossOrigin( origins = "http://localhost:4200" )
public class DashboardController
{

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private PaymentService paymentService;

    @PostMapping( value = "/customerCount" )
    public CustemerCountDTO customerCount()
    {
    	CustemerCountDTO customerCountDTO = new CustemerCountDTO();
        int total = customerService.getTotalCount();
        int active = customerService.getActiveCustomers().size();
        int aboutToDue = customerService.getAboutToDueCustomers().size();

        customerCountDTO.setTotalCustomers( total );
        customerCountDTO.setActiveCustomers( active );
        customerCountDTO.setAboutToDueCustomers( aboutToDue );
        
        customerCountDTO.setInActiveCustomers( total - active - aboutToDue );

        return customerCountDTO;
    }
    
    @PostMapping( value = "/paymentCount" )
    public PaymentCountDTO paymentCount()
    {
    	PaymentCountDTO paymentCountDTO = new PaymentCountDTO();
    	
    	int todayCount = paymentService.getTodayCount();
    	int weeklyCount = paymentService.getWeeklyCount();
    	int monthlyCount = paymentService.getMonthlyCount();
    	
    	int todayCollection = paymentService.getTodayCollection();
    	int weeklyCollection = paymentService.getWeeklyCollection();
    	int monthlyCollection = paymentService.getMonthlyCollection();
    	
    	paymentCountDTO.setTodayCollection( todayCollection );
    	paymentCountDTO.setWeeklyCollection( weeklyCollection );
    	paymentCountDTO.setMonthlyCollection( monthlyCollection );
    	paymentCountDTO.setTodayCount( todayCount );
    	paymentCountDTO.setMonthlyCount( monthlyCount );
    	paymentCountDTO.setWeeklyCount( weeklyCount );

        return paymentCountDTO;
    }
    
    @PostMapping( value = "/monthWiseCollection" )
    public List<MonthWisePaymentDTO> monthWiseCollection( @RequestBody Integer year )
    {
        return paymentService.getMonthWiseCollection( year );
    }
}
