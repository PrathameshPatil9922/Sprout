package com.example.securityweb.Controller;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.securityweb.Entity.Booking;
import com.example.securityweb.Entity.Driver;
import com.example.securityweb.Entity.Farmer;
import com.example.securityweb.Entity.Owner;
import com.example.securityweb.Entity.Schedule;
import com.example.securityweb.Entity.Tractor;
import com.example.securityweb.Repository.ScheduleRepository;
import com.example.securityweb.Service.BookingService;
import com.example.securityweb.Service.DriverService;
import com.example.securityweb.Service.FarmerService;
import com.example.securityweb.Service.OwnerService;
import com.example.securityweb.Service.ScheduleService;
import com.example.securityweb.Service.TractorService;




@RequestMapping("/schedule")
@Controller
public class ScheduleController {
	
	 private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);
	
	@Autowired
	BookingService bookingService;
	@Autowired
	FarmerService farmerService;
	@Autowired
	ScheduleService scheduleService;
	@Autowired
	DriverService driverService;
	@Autowired
	TractorService tractorService;
	@Autowired
	OwnerService ownerService;

	 @GetMapping("/confirm_booking/fetch")
	    public String getTodaysBookings(Model model) {
		 LocalDate bookingDate = LocalDate.now();   
		 List<Booking> booking = bookingService.getBookings(bookingDate);
	        model.addAttribute("Bookings", booking);
	        for (Booking book : booking) {
	            
	            book.getFarmer();
	            book.getDriver();
	            book.getOwner();
	            book.getTractor();
	        }
	 return "Bookings";
	 }
	 
	 
	 @GetMapping("/today-schedules")
	    public String getTodaySchedules(Model model) {
	        LocalDate today = LocalDate.now();
	        List<Schedule> schedules = scheduleService.getPendingSchedulesForDate(today);
	        model.addAttribute("schedules", schedules);
	        return "TodaysSchedule"; 
	    }
	 
	 @GetMapping("/today-weight_schedules")
	    public String getTodayWeightSchedules(Model model) {
	        LocalDate today = LocalDate.now();
	        List<Schedule> schedules = scheduleService.getPendingWeightSchedulesForDate(today);
	        model.addAttribute("schedules", schedules);
	        return "TodaysWeightSchedule"; 
	    }
	 
	 @GetMapping("/today-crushings")
	    public String getTodayCrushingSchedules(Model model) {
	        LocalDate today = LocalDate.now();
	        List<Schedule> schedules = scheduleService.getPendingCrushingSchedulesForDate(today);
	        model.addAttribute("schedules", schedules);
	        return "crushing"; 
	    }
	 
	 @PostMapping("/markAsCompleted")
	    public String confirmCrushing(@RequestParam Integer scheduleId) {
		    Schedule schedule = scheduleService.getScheduleById(scheduleId);
		    schedule.setStatus("Crushed");
		   scheduleService.saveSchedule(schedule);
		 return "redirect:/schedule/today-crushings";
	 }
	 
	 @PostMapping("/schedule")
	    public ResponseEntity<?> scheduleBookings() {
	        scheduleService.updateAndSaveBookings();
	        return ResponseEntity.ok("Bookings scheduled successfully");
	    }
	 
	 @GetMapping("/schedulebooking/{bookingId}")
	 public String viewBooking(@PathVariable("bookingId") Long bookingId, Model model) {
	     Booking booking = bookingService.getBookingById(bookingId);

	     
	     Farmer farmer = booking.getFarmer();
	     Owner owner = booking.getOwner();
	     Tractor tractor = booking.getTractor();
	     Driver driver = booking.getDriver();

	     model.addAttribute("booking", booking);
	     model.addAttribute("farmer", farmer);
	     model.addAttribute("owner", owner);
	     model.addAttribute("tractor", tractor);
	     model.addAttribute("driver", driver);
	     return "bookingform"; 
	 }
	 
	 @PostMapping("/schedule-confirm")
	 public String confirmBooking(@ModelAttribute ScheduleForm form, Model model, RedirectAttributes redirectAttributes,
			 @RequestParam("driverId") Integer driverId, 
             @RequestParam("tractorId") Integer tractorId,
             @RequestParam("ownerId") Integer ownerId,
             @RequestParam("farmerId") Integer farmerId,
             @RequestParam("bookingId") Long bookingId,
             @RequestParam("nooftrolleys") Integer numberoftrolleys,
             @RequestParam("location") String location,
             @RequestParam LocalDate bookingDate,
             @RequestParam LocalTime bookingTime) {
		 
        Driver driver = driverService.findById(driverId);
        Tractor tractor = tractorService.findById(tractorId);
        Owner owner = ownerService.findById(ownerId);
        Farmer farmer = farmerService.findById(farmerId);	 
		 
	   
	    Schedule schedule = new Schedule();
	    schedule.setBookingId(bookingId);
	    schedule.setBookingDate(bookingDate);
	    schedule.setBookingTime(bookingTime);
	    schedule.setFarmer(farmer);
	    schedule.setDriver(driver);
	    schedule.setOwner(owner);
	    schedule.setTractor(tractor);
	    schedule.setLocation(location);
	    schedule.setNumberOfTrolleys(numberoftrolleys);
	    scheduleService.saveSchedule(schedule);
	    
	    Booking booking = bookingService.findById(bookingId);
	    if (booking != null) {
	        booking.setStatus("Completed");
	        bookingService.update(booking);
	    }

	    redirectAttributes.addFlashAttribute("message", "Booking confirmed successfully!");
	    return "redirect:/schedule/confirm_booking/fetch";
	}
}
