package com.example.securityweb.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.securityweb.Entity.Achievement;



public interface AchievementRepository extends JpaRepository<Achievement, Serializable> {
    
}

