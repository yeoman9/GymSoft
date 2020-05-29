package com.gymsoft.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymsoft.domain.dto.DashboardDTO;
import com.gymsoft.domain.service.CustomerService;

@RestController
@RequestMapping( "/apis/v1/dashboard" )
@CrossOrigin( origins = "http://localhost:4200" )
public class DashboardController
{

    @Autowired
    private CustomerService customerService;

    @PostMapping( value = "/data" )
    public DashboardDTO data()
    {
        DashboardDTO dashboardDTO = new DashboardDTO();
        int total = customerService.getTotalCount();
        int active = customerService.getActiveCustomers().size();

        dashboardDTO.setTotalCustomers( total );
        dashboardDTO.setActiveCustomers( active );
        dashboardDTO.setInActiveCustomers( total - active );

        return dashboardDTO;
    }
}
