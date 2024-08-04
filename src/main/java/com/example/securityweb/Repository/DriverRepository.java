package com.example.securityweb.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.securityweb.Entity.Driver;
import com.example.securityweb.Entity.Owner;




@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
	List<Driver> findByOwner(Owner owner);
	Optional<Driver> findByName(String name);
	     Optional<Driver> findById(Integer DriverId);
}
