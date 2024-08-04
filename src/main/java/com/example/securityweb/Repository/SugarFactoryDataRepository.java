package com.example.securityweb.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.securityweb.Entity.SugarFactoryData;


@Repository
public interface SugarFactoryDataRepository extends JpaRepository<SugarFactoryData, Long> {
	List<SugarFactoryData> findBySeason(String season);
}

