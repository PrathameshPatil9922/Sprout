package com.example.securityweb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.securityweb.Entity.Sector;



@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
}