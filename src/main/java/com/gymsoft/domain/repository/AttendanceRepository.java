package com.gymsoft.domain.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymsoft.domain.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>
{

    Optional<Attendance> findById( Long id );

    Optional<Attendance> findByCustomerIdAndDate( Long id, Date date );

    List<Attendance> findByCustomerId( Long id );
}
