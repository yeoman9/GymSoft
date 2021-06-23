package com.gymsoft.domain.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gymsoft.commons.CustomSuccessResponse;
import com.gymsoft.domain.dto.AttendanceDTO;
import com.gymsoft.domain.entity.Customer;
import com.gymsoft.domain.service.AttendanceService;
import com.gymsoft.domain.service.CustomerService;

@RestController
@CrossOrigin( origins = "http://localhost:4200" )
public class AttendanceController
{

    @Autowired
    private CustomerService customerService;

    @Autowired
    AttendanceService attendanceService;

    @PostMapping( value = "/attendance" )
    public ResponseEntity<Object> data( @RequestBody String pin )
    {
        Optional<Customer> customer = customerService.getCustomerByPin( pin );
        if( !customer.isPresent() )
        {
            throw new RuntimeException( "Invalid PIN" );
        }

        if( customer.get().isDeleted() ) 
        {
        	throw new RuntimeException( "Invalid PIN" );
        }
        
        attendanceService.addAttendance( customer.get() );

        CustomSuccessResponse body = new CustomSuccessResponse();
        body.setMessage( "Welcome " + customer.get().getName() + "!" );
        body.setTimestamp( LocalDateTime.now() );
        return new ResponseEntity<>( body, HttpStatus.OK );
    }

    @PostMapping( value = "/attendance/detail" )
    public List<AttendanceDTO> detail( @RequestBody Long id )
    {
        return attendanceService.getAttendanceFromCustomerId( id );
    }
}
