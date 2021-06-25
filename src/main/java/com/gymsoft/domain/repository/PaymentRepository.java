package com.gymsoft.domain.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gymsoft.domain.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>
{

    Optional<Payment> findById( Long id );

    List<Payment> findByCustomerId( Long id );

    List<Payment> findByCustomerIdAndPaymentTo( Long customerId, Date date );
    
    @Query( value = "select * from Payment p where CAST(created_Date AS date)=current_date", nativeQuery = true ) 
    List<Payment> todayData();
    
    @Query( value = "select * from Payment p where CAST(created_Date AS date) BETWEEN (current_date - INTERVAL '7' DAY) and current_date", nativeQuery = true ) 
    List<Payment> weeklyData();
    
    @Query( value = "select * from Payment p where CAST(created_Date AS date) BETWEEN (current_date - INTERVAL '1' MONTH) and current_date", nativeQuery = true ) 
    List<Payment> monthlyData();
}
