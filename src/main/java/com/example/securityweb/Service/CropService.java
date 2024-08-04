package com.example.securityweb.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.securityweb.Entity.Crops;
import com.example.securityweb.Repository.CropRepository;

import java.util.List;

@Service
public class CropService {
 @Autowired
 private CropRepository cropRepository;

 public List<Crops> getAllCrops() {
     return cropRepository.findAll();
 }
}

