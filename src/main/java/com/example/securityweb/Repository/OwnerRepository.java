package com.example.securityweb.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.securityweb.Entity.Owner;
import com.example.securityweb.Entity.Sector;



@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    List<Owner> findByVillageName(String villageName);
    List<Owner> findBySector(Sector sector);
    Optional<Owner> findById(Integer ownerId);
}
