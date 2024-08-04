package com.example.securityweb.Service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.securityweb.Entity.Booking;
import com.example.securityweb.Repository.BookingRepository;

import java.time.LocalDate;
import java.util.List;



@Service
@Transactional
public class BookingService {

    private final BookingRepository bookingRepository;

    
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

   /* public Booking findById(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        return optionalBooking.orElse(null);
    }*/
    
    public List<Booking> getBookings(LocalDate bookingDate) {
        return bookingRepository.findByBookingDate(bookingDate);
    }

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }
    
    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

	public Booking getBookingById(Long bookingId) {
		return bookingRepository.findById(bookingId).orElse(null);
	}
	
	 public void update(Booking booking) {
	        bookingRepository.save(booking);
	    }
    
    
}
