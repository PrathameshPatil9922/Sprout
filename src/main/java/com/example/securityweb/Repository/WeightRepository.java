package com.example.securityweb.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.securityweb.Entity.Weight;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Integer> {
}

