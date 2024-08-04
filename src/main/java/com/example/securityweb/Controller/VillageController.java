package com.example.securityweb.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.securityweb.Entity.Village;
import com.example.securityweb.Service.VillageService;



@RestController
@RequestMapping("/api/villages")
public class VillageController {

    private final VillageService villageService;

    public VillageController(VillageService villageService) {
        this.villageService = villageService;
    }
    
    @GetMapping("/villages")
    public String getAllVillages(Model model) {
        model.addAttribute("villages", villageService.getAllVillages());
        return "village";
    }

    @PostMapping("/add")
    public ResponseEntity<Village> addVillage(@RequestBody Village village) {
        Village savedVillage = villageService.saveVillage(village);
        return new ResponseEntity<>(savedVillage, HttpStatus.CREATED);
    }
}

