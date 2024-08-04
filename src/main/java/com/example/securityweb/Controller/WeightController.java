package com.example.securityweb.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.securityweb.Entity.Driver;
import com.example.securityweb.Entity.Farmer;
import com.example.securityweb.Entity.Owner;
import com.example.securityweb.Entity.Schedule;
import com.example.securityweb.Entity.Tractor;
import com.example.securityweb.Entity.Weight;
import com.example.securityweb.Service.DriverService;
import com.example.securityweb.Service.FarmerService;
import com.example.securityweb.Service.OwnerService;
import com.example.securityweb.Service.ScheduleService;
import com.example.securityweb.Service.TractorService;
import com.example.securityweb.Service.WeightService;


@Controller
public class WeightController {
	
	private static final Logger logger = LoggerFactory.getLogger(WeightController.class);

    @Autowired
    private WeightService weightService;

    @Autowired
    private ScheduleService scheduleService;
    
    @Autowired
    private FarmerService farmerService;
    
    @Autowired
    private DriverService driverService;
    
    @Autowired
    private TractorService tractorService;
    
    @Autowired
    private OwnerService ownerService;

    
    
    @PostMapping("/weighingForm")
    public String showWeighingForm(Model model,
    		@RequestParam Integer scheduleId,
    		@RequestParam Integer farmerId,
    		@RequestParam Integer driverId,
    		@RequestParam Integer tractorId,
    		@RequestParam Integer ownerId) {
    	
        Schedule schedule = scheduleService.getScheduleById(scheduleId);
       logger.info("Id : " + schedule.getScheduleId());
        Farmer farmer = farmerService.findById(farmerId); 
        Driver driver = driverService.findById(driverId); 
        Tractor tractor = tractorService.findById(tractorId); 
        Owner owner = ownerService.findById(ownerId); 
        
        model.addAttribute("farmer", farmer);
        model.addAttribute("driver", driver);
        model.addAttribute("tractor", tractor);
        model.addAttribute("owner", owner);
        model.addAttribute("schedule",schedule);
        
        return "weighingform"; 
    }

    @PostMapping("/saveWeight")
    public String saveWeightAndStatus(@RequestParam("scheduleId") Integer scheduleId,
    		                          @RequestParam("farmerId") Integer farmerId,
                                      @RequestParam("trolley1Weight") double trolley1Weight,
                                      @RequestParam("trolley2Weight") double trolley2Weight,
                                      @RequestParam("totalWeight") double totalWeight) {
        
        Weight weight = new Weight();
        weight.setFarmerId(farmerId);
        weight.setTrolley1Weight(trolley1Weight);
        weight.setTrolley2Weight(trolley2Weight);
        weight.setTotalWeight(totalWeight);
        weightService.saveWeight(weight);

        
        Schedule schedule = scheduleService.getScheduleById(scheduleId);
        Driver driver = schedule.getDriver();
        driver.setAllocated(false);
        Tractor tractor = schedule.getTractor();
        tractor.setAllocated(false);
        schedule.setStatus("completed");
        scheduleService.saveSchedule(schedule);

        return "redirect:/schedule/today-weight_schedules"; 
    }
}

