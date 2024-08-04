package com.example.securityweb.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.securityweb.Entity.Sector;
import com.example.securityweb.Service.SectorService;



@Controller
@RequestMapping("/sector")
public class SectorController {

    private final SectorService sectorService;

    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping("/villages")
    public String getVillageBySector(Model model) {
    	List<Sector> sectors = sectorService.getAllSectors();
    	model.addAttribute("sectors", sectors);
    	return "villageBySector";
    }
    
    @PostMapping("/add")
    public ResponseEntity<Sector> addSector(@RequestBody Sector sector) {
        Sector savedSector = sectorService.saveSector(sector);
        return new ResponseEntity<>(savedSector, HttpStatus.CREATED);
    }
}
