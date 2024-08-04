package com.example.securityweb.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.securityweb.Entity.ContactForm;
import com.example.securityweb.Repository.ContactRepository;

@Service
public class ContactFormService {

    @Autowired
    private ContactRepository contactFormRepository;

    public ContactForm saveContactForm(ContactForm contactForm) {
        return contactFormRepository.save(contactForm);
    }
}

