package com.example.securityweb.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.securityweb.Entity.Booking;
import com.example.securityweb.Entity.BookingForm;
import com.example.securityweb.Entity.Driver;
import com.example.securityweb.Entity.Farmer;
import com.example.securityweb.Entity.Owner;
import com.example.securityweb.Entity.Sector;
import com.example.securityweb.Entity.TodaysBooking;
import com.example.securityweb.Entity.Tractor;
import com.example.securityweb.Entity.Village;
import com.example.securityweb.Service.BookingService;
import com.example.securityweb.Service.DriverService;
import com.example.securityweb.Service.FarmerService;
import com.example.securityweb.Service.OwnerService;
import com.example.securityweb.Service.TodaysBookingService;
import com.example.securityweb.Service.TractorService;
import com.example.securityweb.Service.VillageService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BookingController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

	@Autowired
   private  BookingService bookingService;
	@Autowired
    private  FarmerService farmerService;
	@Autowired
    private  OwnerService ownerService;
	@Autowired
    private  DriverService driverService;
	@Autowired
    private  TractorService tractorService;
	@Autowired
    private  VillageService villageService;
	@Autowired
    private  TodaysBookingService todaysBookingService;
    

   /*public BookingController(BookingService bookingService, TodaysBookingService todaysBookingService) {
        this.bookingService = bookingService;
        this.todaysBookingService = todaysBookingService;
   }*/
    
    
    	@GetMapping("/booking/{id}/view")
        public String viewBooking(@PathVariable Long id, @RequestParam("location") String villageName, @RequestParam("farmerId") Integer farmerId, Model model) {
    
            Farmer farmer = farmerService.findById(farmerId);
            TodaysBooking todaysbooking = todaysBookingService.findById(id);

            Village farmerVillage = villageService.findByName(villageName);
            Sector farmerSector = farmerVillage.getSector();
            
            logger.info("Sector : " + farmerSector.getName());
            List<Owner> ownersInSameSector = ownerService.findOwnersBySector(farmerSector);
            logger.info("owner : " + ownersInSameSector);
        List<Driver> availableDrivers = new ArrayList<>();
        List<Tractor> availableTractors = new ArrayList<>();
        
                
        for (Owner owner : ownersInSameSector) {
        	System.out.println("Owner: " + owner.getName());
        	 logger.info("owner : " + owner.getName());
            List<Driver> drivers = driverService.findByOwner(owner);
            List<Tractor> tractors = tractorService.findByOwner(owner);
            for (Driver driver : drivers) {
                if (!driver.isAllocated()) {
                    availableDrivers.add(driver);
                }
            }
            for (Tractor tractor : tractors) {
                if (!tractor.isAllocated()) {
                    availableTractors.add(tractor);
                }
            }
        }
        
        Owner owner = new Owner();
        model.addAttribute("farmer", farmer);
        model.addAttribute("booking", todaysbooking);
        model.addAttribute("owners", ownersInSameSector);
        model.addAttribute("drivers", availableDrivers);
        model.addAttribute("tractors", availableTractors);
        model.addAttribute("owner", owner);
        model.addAttribute("driver", new Driver());
        model.addAttribute("tractor", new Tractor());
        return "viewbooking";
    }

    	@PostMapping("/confirm-booking")
    	public String confirmBooking(@ModelAttribute BookingForm bookingForm, 
    	                             @RequestParam("driverId") Integer driverId, 
    	                             @RequestParam("tractorId") Integer tractorId,
    	                             @RequestParam("ownerId") Integer ownerId,
    	                             @RequestParam("farmerId") Integer farmerId,
    	                             @RequestParam LocalDate bookingDate,
                                     @RequestParam LocalTime bookingTime,
    	                             RedirectAttributes redirectAttributes) {
    	
    	    Driver selectedDriver = driverService.findById(driverId);
    	    Tractor selectedTractor = tractorService.findById(tractorId);
    	    Owner selectedOwner = ownerService.findById(ownerId);
    	    
    	    Farmer farmer = farmerService.findById(farmerId);
    	    System.out.println(
    	    	"Farmer details: "	+ farmer.getPhone());
    	   
    	    
    	   
    	    Booking booking = new Booking();
    	    booking.setBookingDate(bookingDate); 
    	    booking.setBookingTime(bookingTime);
    	    booking.setFarmer(farmer);
    	    booking.setOwner(selectedOwner);
    	    booking.setTractor(selectedTractor); 
    	    booking.setDriver(selectedDriver);   
    	    
    	    // Save the booking to the database
    	    bookingService.save(booking);
    	    
    	    // Update the allocated status of the selected driver and tractor
    	    selectedDriver.setAllocated(true);
    	    driverService.update(selectedDriver);
    	    
    	    selectedTractor.setAllocated(true);
    	    tractorService.update(selectedTractor);

    	    // Update the status in TodaysBooking table
    	    TodaysBooking todaysBooking = todaysBookingService.findById(bookingForm.getBookingId());
    	    if (todaysBooking != null) {
    	        todaysBooking.setStatus("Completed");
    	        todaysBookingService.update(todaysBooking);
    	    }

    	    // Redirect with success message
    	    redirectAttributes.addFlashAttribute("message", "Booking confirmed successfully!");
    	    return "redirect:/todays-bookings/fetch";
    	}

    
}
