package com.example.securityweb.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.securityweb.Entity.Sector;
import com.example.securityweb.Entity.Village;
import com.example.securityweb.Repository.VillageRepository;


@Service
public class VillageService {

    private final VillageRepository villageRepository;

    public VillageService(VillageRepository villageRepository) {
        this.villageRepository = villageRepository;
    }

    public Village saveVillage(Village village) {
        return villageRepository.save(village);
    }
    
    public Village findByName(String villageName) {
        return villageRepository.findByName(villageName);
    }

	public Sector getSectorId(String villagename) {
		Village village = villageRepository.findByName(villagename);
		 if (village != null) {
	            // Assuming the Village entity has a reference to the Sector entity
	            return village.getSector();
	        } else {
	            
	            return null;
	        }
	}

	public List<Village> getAllVillages() {
		return villageRepository.findAll();
	}
}

