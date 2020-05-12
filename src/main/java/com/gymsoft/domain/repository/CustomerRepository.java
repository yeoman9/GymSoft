package com.gymsoft.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymsoft.domain.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findById(Long id);
	Optional<Customer> findByEmail(String email);
	Optional<Customer> findByMobile(String mobile);
	Optional<Customer> findByPin(String pin);
	List<Customer> findByNameContainingIgnoreCase(String searchKey);
	List<Customer> findAll();
	
}
