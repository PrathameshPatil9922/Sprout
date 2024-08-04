package com.example.securityweb.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.securityweb.Entity.Crops;


public interface CropRepository extends JpaRepository<Crops, Long> {
}

