package com.example.securityweb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class RegistrationSuccessController {

    @GetMapping("/registration_success")
    public String showRegistrationSuccessPage(@ModelAttribute("farmerId") Integer farmerId, Model model) {
        model.addAttribute("farmerId", farmerId);
        return "registration_success";
    }
}

