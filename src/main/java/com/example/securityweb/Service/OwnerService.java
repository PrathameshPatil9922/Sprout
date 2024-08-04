package com.example.securityweb.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.securityweb.Entity.Owner;
import com.example.securityweb.Entity.Sector;
import com.example.securityweb.Repository.OwnerRepository;




@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    
    public Owner addOwner(Owner owner) {
        return ownerRepository.save(owner);
    }
    
    public List<Owner> findOwnersBySector(Sector sector) {
        return ownerRepository.findBySector(sector);
    }
    

	public Owner findById(Integer ownerId) {
		return ownerRepository.findById(ownerId).orElse(null);
	}

	public void updateOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    // Delete Owner
    public void deleteOwner(Integer ownerId) {
        ownerRepository.deleteById(ownerId);
    }
    
}
