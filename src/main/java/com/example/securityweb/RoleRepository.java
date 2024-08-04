package com.example.securityweb;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer>{

	Role findByName(String string);

}
