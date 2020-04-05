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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymsoft.commons.error.CustomSuccessResponse;
import com.gymsoft.domain.dto.CustomerDTO;
import com.gymsoft.domain.services.CustomerService;
import com.gymsoft.domain.user.entity.Customer;
import com.gymsoft.domain.user.entity.User;

@RestController
@RequestMapping("/apis/v1/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

	@Autowired
    private CustomerService customerService;
	
	
	@PostMapping(value="/add")
    public ResponseEntity<CustomSuccessResponse> create(@ModelAttribute CustomerDTO customerDto){
         customerService.addCustomer(customerDto);
         
         CustomSuccessResponse body = new CustomSuccessResponse();
         body.setMessage("Customer created successfully..");
         body.setTimestamp(LocalDateTime.now());
         return new ResponseEntity<>(body, HttpStatus.OK);
    }
	
	@PostMapping(value="/list")
    public List<Customer> lisAll(){
         return customerService.getAllCustomers();
    }
		
}
