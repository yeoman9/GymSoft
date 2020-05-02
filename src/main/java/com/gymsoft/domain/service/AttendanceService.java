package com.gymsoft.domain.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.gymsoft.domain.entity.Attendance;
import com.gymsoft.domain.entity.Customer;
import com.gymsoft.domain.repository.AttendanceRepository;

@Service("attendanceService")
public class AttendanceService{

	private AttendanceRepository attendanceRepository;
	
	AttendanceService(AttendanceRepository attendanceRepository){
		this.attendanceRepository = attendanceRepository;
	}
	
	public Attendance addAttendance(Customer customer) {
		
		Optional<Attendance> attendanceEntry = attendanceRepository.findByCustomerIdAndDate(customer.getId(), new Date());
		
		if (attendanceEntry.isPresent()) {

			throw new RuntimeException("Attendance already done for the day. Enjoy your workout.");
		}
		
		if(!customer.isActive()) {
			Attendance attendance = new Attendance();
			attendance.setCustomer(customer);
			attendance.setDate(new Date());
			attendance.setStatus("InActive");
			
			attendanceRepository.save(attendance);
			
			throw new RuntimeException("Your payment is due from "+ customer.getLastDate() +". Kindly make your payment to continue use gym facilities.");
		}
		
		Attendance attendance = new Attendance();
		attendance.setCustomer(customer);
		attendance.setDate(new Date());
		attendance.setStatus("Active");
		
		attendanceRepository.save(attendance);
		
		return attendance;
		
	}
	
}
