package com.example.securityweb;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

 
public interface UserRepository extends JpaRepository<User, Integer> {
 
	Optional<User> findByUsernameOrEmail(String usernameOrEmail, String orEmail);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

	
}
