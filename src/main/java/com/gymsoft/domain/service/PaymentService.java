package com.gymsoft.domain.service;

import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymsoft.domain.dto.MonthWisePaymentDTO;
import com.gymsoft.domain.dto.PaymentDTO;
import com.gymsoft.domain.entity.Customer;
import com.gymsoft.domain.entity.Payment;
import com.gymsoft.domain.repository.PaymentRepository;

@Service( "paymentService" )
public class PaymentService
{

	static final Map<String, TemporalAdjuster> ADJUSTERS = new HashMap<>();
	{
		ADJUSTERS.put("day", TemporalAdjusters.ofDateAdjuster(d -> d)); // identity
		ADJUSTERS.put("week", TemporalAdjusters.previousOrSame(DayOfWeek.of(1)));
		ADJUSTERS.put("month", TemporalAdjusters.firstDayOfMonth());
		ADJUSTERS.put("year", TemporalAdjusters.firstDayOfYear());
	}
	
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
                payment.setMode( paymentDTO.getMode() );
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

	public int getTodayCount() {
		
		return paymentRepository.todayData().size();
	}
	
	public int getTodayCollection() {
		
		return paymentRepository.todayData()
				.stream()
				.mapToInt( payment -> payment.getAmount() )
				.sum();
	}
	
	public int getWeeklyCount() {
		
		return paymentRepository.weeklyData().size();
	}
	
	public int getWeeklyCollection() {
		
		return paymentRepository.weeklyData()
				.stream()
				.mapToInt( payment -> payment.getAmount() )
				.sum();
	}
	
	public int getMonthlyCount() {
		
		return paymentRepository.monthlyData().size();
	}
	
	public int getMonthlyCollection() {
		
		return paymentRepository.monthlyData()
				.stream()
				.mapToInt( payment -> payment.getAmount() )
				.sum();
	}
	
	public List<MonthWisePaymentDTO> getMonthWiseCollection( int year )
	{		
		 return paymentRepository.getMonthWisePayments( year );
	}

}
