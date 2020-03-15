package com.gymsoft.domain.services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gymsoft.domain.dto.CustomerDTO;
import com.gymsoft.domain.user.entity.Customer;
import com.gymsoft.domain.user.repository.CustomerRepository;

@Service("customerService")
public class CustomerService{

	private CustomerRepository customerRepository;
	
	CustomerService(CustomerRepository customerRepository){
		this.customerRepository = customerRepository;
	}
	
	public Customer addCustomer(CustomerDTO customerDto) {
		
		Objects.requireNonNull(customerDto);
		
		String customerEmail = customerDto.getEmail();
		
		Optional<Customer> customerFound = customerRepository.findByEmail(customerEmail);
		
		if(customerFound.isPresent()) {
			throw new RuntimeException("Customer already exist with this email");
		}
		
		Customer customer = new Customer();
		customer.setName(customerDto.getName());				
		customer.setEmail(customerDto.getEmail());	
		customer.setMobile(customerDto.getMobile());
		customer.setDateOfJoin(customerDto.getDateOfJoin());
		customer.setLastDate(customerDto.getLastDate());
		customer.setGender(customerDto.getGender());
		customer.setPin(customerDto.getPin());		
		
		customerRepository.save(customer);
		
		return customer;
		
	}
	
	public Optional<Customer> getCustomer(String email)
	{		
		return customerRepository.findByEmail(email);
	}


}
