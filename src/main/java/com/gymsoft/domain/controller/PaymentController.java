package com.gymsoft.domain.controller;


import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gymsoft.commons.CustomSuccessResponse;
import com.gymsoft.domain.entity.Customer;
import com.gymsoft.domain.service.CustomerService;
import com.gymsoft.domain.service.PaymentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {

	@Autowired
    private CustomerService customerService;
	
	@Autowired
	PaymentService paymentService;
		
	
}
