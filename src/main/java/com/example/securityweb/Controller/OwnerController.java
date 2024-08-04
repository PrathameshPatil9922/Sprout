package com.example.securityweb.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.securityweb.Entity.Driver;
import com.example.securityweb.Entity.Owner;
import com.example.securityweb.Entity.Sector;
import com.example.securityweb.Repository.DriverRepository;
import com.example.securityweb.Repository.TractorRepository;
import com.example.securityweb.Service.OwnerService;
import com.example.securityweb.Service.VillageService;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;
    @Autowired
    private VillageService villageService;
    private DriverRepository driverRepository;
    private TractorRepository tractorRepository;
   
	 private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);
  
    @GetMapping("/ownerdash")
    public String ownerDash(Model model) {
    model.addAttribute("owners", ownerService.getAllOwners());
    return "ownerDash";
    }

    // Add Owner
    
    @GetMapping("/add")
    public String addform() {
    	return "addOwner";
    }
    
    @GetMapping("/update")
    public String updateform() {
    	return "updateOwnerForm";
    }
    
    @GetMapping("/delete")
    public String deleteform() {
    	return "deleteOwnerForm";
    }
    
    @PostMapping("/add")
    public String addOwner(@ModelAttribute Owner owner) {
     String villagename = owner.getVillageName();
     Sector sector = villageService.getSectorId(villagename);
     
     logger.info("Sector: " + sector.getName());
         owner.setSector(sector);
        ownerService.addOwner(owner);
        return "redirect:/";
    }

  
    @PostMapping("/update")
    public String updateOwner(@ModelAttribute Owner owner) {
        ownerService.updateOwner(owner);
        return "redirect:/";
    }

    
    @PostMapping("/delete")
    public String deleteOwner(@RequestParam("ownerId") Integer ownerId) {
        ownerService.deleteOwner(ownerId);
        return "redirect:/";
    }
    
    @GetMapping("/details")
    public String viewOwnerDetails(int ownerId, Model model) {
        Owner owner = ownerService.findById(ownerId);
        model.addAttribute("owner", owner);
        return "ownersDetails";
    }
}
