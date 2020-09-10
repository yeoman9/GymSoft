package com.gymsoft.domain.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymsoft.domain.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>
{

    Optional<Payment> findById( Long id );

    Optional<Payment> findByCustomerIdAndDate( Long id, Date date );

    List<Payment> findByCustomerId( Long id );
}