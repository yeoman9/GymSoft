package com.gymsoft.domain.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymsoft.domain.user.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findById(Long id);
	Optional<Customer> findByEmail(String email);
	Optional<Customer> findByMobile(String mobile);
	List<Customer> findAll();
	
}
