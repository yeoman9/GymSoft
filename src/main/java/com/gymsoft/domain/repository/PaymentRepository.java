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
    
    @Query( value = "SELECT *\n"
    		+ "FROM payment\n"
    		+ "WHERE date_trunc('week', now()) <= created_date AND\n"
    		+ "created_date < date_trunc('week', now()) + interval '1 week'", nativeQuery = true ) 
    List<Payment> weeklyData();
    
    @Query( value = "SELECT *\n"
    		+ "FROM payment\n"
    		+ "WHERE date_trunc('month', now()) <= created_date AND\n"
    		+ "created_date < date_trunc('month', now()) + interval '1 month'", nativeQuery = true ) 
    List<Payment> monthlyData();
}
