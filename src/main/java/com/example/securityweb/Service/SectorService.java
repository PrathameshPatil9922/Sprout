package com.example.securityweb.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.securityweb.Entity.Sector;
import com.example.securityweb.Repository.SectorRepository;



@Service
public class SectorService {

    private final SectorRepository sectorRepository;

    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    public Sector saveSector(Sector sector) {
        return sectorRepository.save(sector);
    }
    
    public List<Sector> getAllSectors() {
        return sectorRepository.findAll();
    }
}
