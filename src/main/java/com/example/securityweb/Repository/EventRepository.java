package com.example.securityweb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.securityweb.Entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
   
}

