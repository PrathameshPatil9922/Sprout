package com.example.securityweb.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.securityweb.Entity.Crops;
import com.example.securityweb.Service.CropService;
import com.example.securityweb.Service.FertilizerDataService;



@Controller
public class FarmerPortalController {
	
	@Autowired
	CropService cropService;
	
	@Autowired
	FertilizerDataService fertilizerDataService;

    @GetMapping("/crops")
    public String getCrops(Model model) {
        List<Crops> crops = cropService.getAllCrops();
        model.addAttribute("crops", crops);
        return "crops"; 
    }
    
    @GetMapping("/analytics")
    public String analytics() {
        return "analytics"; 
    }
    
   
    
    @GetMapping("/fertilizer")
    public String showFertilizerData(Model model) {
        model.addAttribute("fertilizerData", fertilizerDataService.getAllFertilizerData());
        return "fertilizer";
    }
    
}

