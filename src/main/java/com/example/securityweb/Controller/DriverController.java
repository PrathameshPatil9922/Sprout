

package com.example.securityweb.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.securityweb.Entity.Driver;
import com.example.securityweb.Entity.Owner;
import com.example.securityweb.Repository.OwnerRepository;
import com.example.securityweb.Service.DriverService;



@Controller
public class DriverController {
    
    private final DriverService driverService;
    private final OwnerRepository ownerRepository;

    public DriverController(DriverService driverService,OwnerRepository ownerRepository) {
        this.driverService = driverService;
        this.ownerRepository = ownerRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<Driver> addTractor(@RequestBody Driver driver) {
    	Driver savedDriver = driverService.saveDriver(driver);
        return new ResponseEntity<>(savedDriver, HttpStatus.CREATED);
    }
    
    @GetMapping("/drivers/add")
    public String showAddDriverForm(@RequestParam("ownerId") Integer ownerId, Model model) {
        // Pass the ownerId to the view so that it can be used in the form
    	Integer ownerIdInt = Integer.valueOf(ownerId);
        model.addAttribute("ownerId", ownerIdInt);
        return "addDriverForm"; // Assuming you have a template for the add driver form
    }
    
    @PostMapping("/drivers/add")
    public String addDriver(@RequestParam("driverName") String driverName, @RequestParam("ownerId") Integer ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElse(null);
        if (owner != null) {
            Driver driver = new Driver();
            driver.setName(driverName);
            driver.setOwner(owner);
            driverService.saveDriver(driver);
        }
        return "redirect:/owners/details?ownerId=" + ownerId;
    }
}


