package com.example.securityweb.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.securityweb.Entity.Owner;
import com.example.securityweb.Entity.Tractor;
import com.example.securityweb.Repository.TractorRepository;



@Service
public class TractorService {

    private final TractorRepository tractorRepository;

    public TractorService(TractorRepository tractorRepository) {
        this.tractorRepository = tractorRepository;
    }

    public Tractor saveTractor(Tractor tractor) {
        return tractorRepository.save(tractor);
    }
    
    public List<Tractor> findByOwner(Owner owner) {
        return tractorRepository.findByOwner(owner);
    }
    
    public Optional<Tractor> findByName(String name) {
        return tractorRepository.findByName(name);
    }

    public void update(Tractor tractor) {
        tractorRepository.save(tractor);
    }

	public Tractor findById(Integer tractorId) {
		return tractorRepository.findById(tractorId).orElse(null);
	}
}

