package com.example.securityweb.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.securityweb.Entity.TodaysBooking;
import com.example.securityweb.Service.TodaysBookingService;



@Controller
@RequestMapping("/todays-bookings")
public class TodaysBookingController {
    @Autowired
    private TodaysBookingService todaysBookingService;

    @GetMapping("/fetch")
    public String getTodaysBookings(Model model) {
    	  LocalDate bookingDate = LocalDate.now();
        List<TodaysBooking> todaysBookings = todaysBookingService.getTodaysBookings(bookingDate);
        
        model.addAttribute("Bookings", todaysBookings);
        return "todaysBookings"; 
    }
    
    @GetMapping("/book")
    public String boookCane(Model model) {
        model.addAttribute("todaysBooking", new TodaysBooking());
        return "book"; 
    }
    
    @PostMapping("/book")
    public String saveTodaysBooking(@ModelAttribute TodaysBooking todaysBooking) {
        TodaysBooking savedBooking = todaysBookingService.saveTodaysBooking(todaysBooking);
        return "booksuccess";
        
    }
}
