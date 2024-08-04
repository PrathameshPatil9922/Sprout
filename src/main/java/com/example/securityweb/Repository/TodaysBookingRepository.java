package com.example.securityweb.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.securityweb.Entity.TodaysBooking;



@Repository
public interface TodaysBookingRepository extends JpaRepository<TodaysBooking, Long> {
    List<TodaysBooking> findByBookingDate(LocalDate bookingDate);

}

