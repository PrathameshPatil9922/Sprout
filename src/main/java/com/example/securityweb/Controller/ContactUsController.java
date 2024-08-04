package com.example.securityweb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.securityweb.Entity.ContactForm;
import com.example.securityweb.Service.ContactFormService;

@Controller
public class ContactUsController {

    @Autowired
    private ContactFormService contactFormService;

    @PostMapping("/submitContactForm")
    public String submitForm(@RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String subject,
            @RequestParam String message) {
    	
    	ContactForm contactForm = new ContactForm();
        contactForm.setName(name);
        contactForm.setEmail(email);
        contactForm.setPhone(phone);
        contactForm.setSubject(subject);
        contactForm.setMessage(message);

        contactFormService.saveContactForm(contactForm);
        ResponseEntity.status(HttpStatus.CREATED).body("Form submitted successfully!");
        return "redirect:/";
    }
}

