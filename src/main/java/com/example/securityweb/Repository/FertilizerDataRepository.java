package com.example.securityweb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.securityweb.Entity.FertilizerData;

public interface FertilizerDataRepository extends JpaRepository<FertilizerData, Long> {
    
}

