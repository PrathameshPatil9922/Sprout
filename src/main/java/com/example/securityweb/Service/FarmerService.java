package com.example.securityweb.Service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.securityweb.Role;
import com.example.securityweb.RoleRepository;
import com.example.securityweb.User;
import com.example.securityweb.UserRepository;
import com.example.securityweb.Entity.Farmer;
import com.example.securityweb.Exceptions.EmailAlreadyRegisteredException;
import com.example.securityweb.Exceptions.PhoneAlreadyRegisteredException;
import com.example.securityweb.Exceptions.UsernameAlreadyRegisteredException;
import com.example.securityweb.Repository.FarmerRepository;

@Service
public class FarmerService {
	
	 private static final Logger logger = LoggerFactory.getLogger(FarmerService.class);
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private FarmerRepository farmerRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder; 

    public Integer registerFarmer(Farmer farmer,User user) throws Exception {
    	
    	if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyRegisteredException("Email is already registered");
        }

       
        if (farmerRepository.existsByphone(farmer.getPhone())) {
            throw new PhoneAlreadyRegisteredException("Phone number is already registered");
        }

       
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyRegisteredException("Username is already registered");
        }

    	
        
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        
        Role farmerRole = roleRepository.findByName("FARMER");
        if (farmerRole == null) {
            farmerRole = new Role("FARMER");
            roleRepository.save(farmerRole);
        }
        Set<Role> roles = new HashSet<>();
        roles.add(farmerRole);
        user.setRoles(roles);
        
    

        User savedUser = userRepository.save(user);
        logger.info("Registering farmer: {}", farmer.getLocation());
        farmer.setUser(savedUser);
         Farmer savedFarmer = farmerRepository.save(farmer);
         return savedFarmer.getId();
    }

	public Farmer findById(Integer farmerId) {
		return farmerRepository.findById(farmerId).orElseThrow(null);
	}
}

