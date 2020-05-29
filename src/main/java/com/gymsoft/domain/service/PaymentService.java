package com.gymsoft.domain.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.gymsoft.domain.entity.Customer;
import com.gymsoft.domain.entity.Payment;
import com.gymsoft.domain.repository.PaymentRepository;

@Service("paymentService")
public class PaymentService{

	private PaymentRepository paymentRepository;
	
	PaymentService(PaymentRepository paymentRepository){
		this.paymentRepository = paymentRepository;
	}
	
	public Payment addPayment(Customer customer) {
		
		Payment payment = new Payment();
		payment.setCustomer(customer);
		payment.setDate(new Date());
		
		paymentRepository.save(payment);
		
		return payment;
		
	}

	
}
