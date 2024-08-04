package com.example.securityweb.Repository;




import org.springframework.data.jpa.repository.JpaRepository;

import com.example.securityweb.Entity.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByBookingDate(LocalDate bookingDate);
    List<Schedule> findByBookingDateAndStatus(LocalDate bookingDate, String status);
	Schedule getScheduledByscheduleId(Integer farmerId);
	
}

