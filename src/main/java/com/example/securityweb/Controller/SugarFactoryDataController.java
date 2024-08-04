package com.example.securityweb.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.securityweb.Entity.SugarFactoryData;
import com.example.securityweb.Service.SugarFactoryDataService;



@Controller
public class SugarFactoryDataController {
	
    @Autowired
    private SugarFactoryDataService service;
    
    @GetMapping("/FarmerPortal")
    public String FarmerPortal(){
    	return "FarmerPortal";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @RequestParam(required = false, defaultValue = "2022-23") String season) {
        List<SugarFactoryData> factoryDataList = service.findBySeason(season);
        model.addAttribute("factoryDataList", factoryDataList);
        return "dashboard";
    }
}

