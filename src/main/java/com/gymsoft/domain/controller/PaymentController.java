package com.gymsoft.domain.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymsoft.commons.CustomSuccessResponse;
import com.gymsoft.domain.dto.CustomerDTO;
import com.gymsoft.domain.dto.PaymentDTO;
import com.gymsoft.domain.entity.Payment;
import com.gymsoft.domain.service.PaymentService;

@RestController
@RequestMapping( "/apis/v1/payment" )
@CrossOrigin( origins = "http://localhost:4200" )
public class PaymentController
{

    @Autowired
    PaymentService paymentService;
    
    @PostMapping( value = "/add" )
    public ResponseEntity<CustomSuccessResponse> create( @ModelAttribute PaymentDTO paymentDto )
    {
        paymentService.addPayment( paymentDto );

        CustomSuccessResponse body = new CustomSuccessResponse();
        body.setMessage( "Payment added successfully." );
        body.setTimestamp( LocalDateTime.now() );
        return new ResponseEntity<>( body, HttpStatus.OK );
    }
    
    @PostMapping( value = "/list" )
    public List<Payment> list( @RequestBody String query )
    {
        if( "all".equals( query ) )
        {
            return paymentService.getAllPayments();
        }
        else
        {
            try
            {
                Long CustomerId = Long.parseLong( query );
                return paymentService.getPaymentsByCustomerId( CustomerId );
            }
            catch( NumberFormatException e )
            {
                return null;
            }
        }
    }
}
