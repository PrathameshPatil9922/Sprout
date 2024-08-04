package com.example.securityweb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.securityweb.Entity.Owner;
import com.example.securityweb.Entity.Tractor;
import com.example.securityweb.Repository.OwnerRepository;
import com.example.securityweb.Service.TractorService;



@Controller
public class TractorController {
	
	@Autowired
	OwnerRepository ownerRepository;
    
    private final TractorService tractorService;

    public TractorController(TractorService tractorService) {
        this.tractorService = tractorService;
    }
    
    @GetMapping("/tractors/add")
    public String showAddTractorForm(@RequestParam("ownerId") Integer ownerId, Model model) {
        // Pass the ownerId to the view so that it can be used in the form
    	Integer ownerIdInt = Integer.valueOf(ownerId);
        model.addAttribute("ownerId", ownerIdInt);
        return "addTractorForm"; // Assuming you have a template for the add driver form
    }

    @PostMapping("/addtractor")
    public ResponseEntity<Tractor> addTractor(@RequestBody Tractor tractor) {
        Tractor savedTractor = tractorService.saveTractor(tractor);
        return new ResponseEntity<>(savedTractor, HttpStatus.CREATED);
    }
    
    @PostMapping("/tractors/add")
    public String addTractor(@RequestParam("tractorName") String tractorName, 
                             @RequestParam("registrationNumber") String registrationNumber,
                             @RequestParam("ownerId") Integer ownerId) {
        Owner owner = ownerRepository.findById(ownerId).orElse(null);
        if (owner != null) {
            Tractor tractor = new Tractor();
            tractor.setName(tractorName);
            tractor.setRegistrationNumber(registrationNumber);
            tractor.setOwner(owner);
            tractorService.saveTractor(tractor);
        }
        return "redirect:/owners/details?ownerId=" + ownerId;
    }
}

