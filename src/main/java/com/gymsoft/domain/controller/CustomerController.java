package com.gymsoft.domain.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymsoft.commons.CustomSuccessResponse;
import com.gymsoft.domain.dto.CustomerDTO;
import com.gymsoft.domain.entity.Customer;
import com.gymsoft.domain.entity.User;
import com.gymsoft.domain.service.CustomerService;

@RestController
@RequestMapping( "/apis/v1/customer" )
@CrossOrigin( origins = "http://localhost:4200" )
public class CustomerController
{

    @Autowired
    private CustomerService customerService;

    @PostMapping( value = "/add" )
    public ResponseEntity<CustomSuccessResponse> create( @ModelAttribute CustomerDTO customerDto )
    {
        customerService.addCustomer( customerDto );

        CustomSuccessResponse body = new CustomSuccessResponse();
        body.setMessage( "Customer created successfully.." );
        body.setTimestamp( LocalDateTime.now() );
        return new ResponseEntity<>( body, HttpStatus.OK );
    }

    @PostMapping( value = "/list" )
    public List<Customer> list( @RequestBody String query )
    {
        if( "active".equals( query ) )
        {
            return customerService.getActiveCustomers();
        }
        if( "due".equals( query ) )
        {
            return customerService.getDueCustomers();
        }
        if( "aboutToDue".equals( query ) )
        {
            return customerService.getAboutToDueCustomers();
        }
        if( "deleted".equals( query ) ) 
        {
        	return customerService.getDeletedCustomers();
        }
        else
        {
            return customerService.getAllCustomers();
        }
    }

    @PostMapping( value = "/get" )
    public Optional<Customer> getCustomer( @RequestBody Long id )
    {

        return customerService.getCustomer( id );
    }

    @PostMapping( value = "/update" )
    public ResponseEntity<CustomSuccessResponse> update( @ModelAttribute CustomerDTO customerDto )
    {
        customerService.updateCustomer( customerDto );

        CustomSuccessResponse body = new CustomSuccessResponse();
        body.setMessage( "Customer updated successfully.." );
        body.setTimestamp( LocalDateTime.now() );
        return new ResponseEntity<>( body, HttpStatus.OK );
    }

    @PostMapping( value = "/searchByName" )
    public List<Customer> update( @RequestBody String searchKey )
    {
        return customerService.searchByNameContains( searchKey );
    }

}
