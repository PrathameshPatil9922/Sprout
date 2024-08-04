package com.example.securityweb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.securityweb.Entity.ContactForm;

@Repository
public interface ContactRepository extends JpaRepository<ContactForm, Long> {
}

