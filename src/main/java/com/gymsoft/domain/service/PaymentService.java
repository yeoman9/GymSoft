package com.gymsoft.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymsoft.domain.dto.PaymentDTO;
import com.gymsoft.domain.entity.Customer;
import com.gymsoft.domain.entity.Payment;
import com.gymsoft.domain.repository.PaymentRepository;

@Service( "paymentService" )
public class PaymentService
{

    private PaymentRepository paymentRepository;
    
    @Autowired
    CustomerService customerService;

    PaymentService( PaymentRepository paymentRepository )
    {
        this.paymentRepository = paymentRepository;
    }

    public Payment addPayment( PaymentDTO paymentDTO )
    {
        String customerIdString = paymentDTO.getCustomerId();
        Long customerId = Long.valueOf( customerIdString );
        Optional<Customer> customer = customerService.getCustomer( customerId );        
        
        if( customer.isPresent() )
        {
            List<Payment> isSamePayment =
                paymentRepository.findByCustomerIdAndPaymentTo( customerId, paymentDTO.getPaymentTo() );

            if( isSamePayment.isEmpty() )
            {
                Payment payment = new Payment();
                payment.setPaymentFrom( paymentDTO.getPaymentFrom() );
                payment.setPaymentTo( paymentDTO.getPaymentTo() );
                payment.setAmount( Integer.parseInt( paymentDTO.getAmount() ) );
                payment.setMonths( Integer.parseInt( paymentDTO.getMonths() ) );
                payment.setCustomer( customer.get() );

                Customer c = customer.get();
                c.setLastDate( paymentDTO.getPaymentTo() );
                customerService.save( c );

                paymentRepository.save( payment );

                return payment;
            }
            else
            {
                return null;
            }

        }
        else
        {
            throw new RuntimeException( "Customer is required." );
        }
    }

    
    public List<Payment> getAllPayments()
    {
        return paymentRepository.findAll();
    }
    
    public List<Payment> getPaymentsByCustomerId(Long customerId)
    {
        return paymentRepository.findByCustomerId(customerId);
    }

}
