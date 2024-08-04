package com.example.securityweb.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.securityweb.Entity.Weight;
import com.example.securityweb.Repository.WeightRepository;

@Service
public class WeightService {

    @Autowired
    private WeightRepository weightRepository;

  
    public void saveWeight(Weight weight) {
        weightRepository.save(weight);
    }
}
