package com.example.securityweb.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.securityweb.Entity.SugarFactoryData;
import com.example.securityweb.Repository.SugarFactoryDataRepository;



@Service
public class SugarFactoryDataService {
    @Autowired
    private SugarFactoryDataRepository repository;

    public SugarFactoryData save(SugarFactoryData data) {
        return repository.save(data);
    }
    
    public List<SugarFactoryData> findBySeason(String season) {
        return repository.findBySeason(season);
    }

    // Other service methods as needed
}

