package com.example.securityweb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.securityweb.Entity.Sector;
import com.example.securityweb.Entity.Village;



@Repository
public interface VillageRepository extends JpaRepository<Village, Long> {
	Village findByName(String villageName);

	Sector getByName(String villagename);
	
}

