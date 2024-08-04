package com.example.securityweb.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.securityweb.Entity.FertilizerData;
import com.example.securityweb.Repository.FertilizerDataRepository;

import java.util.List;

@Service
public class FertilizerDataService {

    private final FertilizerDataRepository fertilizerDataRepository;

  
    public FertilizerDataService(FertilizerDataRepository fertilizerDataRepository) {
        this.fertilizerDataRepository = fertilizerDataRepository;
    }

    public List<FertilizerData> getAllFertilizerData() {
        return fertilizerDataRepository.findAll();
    }
    
}

