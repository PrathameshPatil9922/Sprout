package com.example.securityweb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.securityweb.Entity.Farmer;

public interface FarmerRepository extends JpaRepository<Farmer, Integer> {

	@Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Farmer f WHERE f.Phone = :phone")
	boolean existsByphone(@Param("phone")String Phone);
    
}

