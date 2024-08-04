package com.example.securityweb.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.securityweb.Entity.Farmer;
import com.example.securityweb.Exceptions.EmailAlreadyRegisteredException;
import com.example.securityweb.Exceptions.PhoneAlreadyRegisteredException;
import com.example.securityweb.Exceptions.UsernameAlreadyRegisteredException;
import com.example.securityweb.Service.FarmerService;
import com.example.securityweb.User;
import com.example.securityweb.DTO.FarmerRegistrationDTO;

@Controller
public class FarmerController {

    private static final Logger logger = LoggerFactory.getLogger(FarmerController.class);

    @Autowired
    private FarmerService farmerService;

    @GetMapping("/register_farmer")
    public String showFarmerRegistrationForm(Model model) {
    	FarmerRegistrationDTO registration = new FarmerRegistrationDTO();
    	registration.setFarmer(new Farmer());
    	registration.setUser(new User());
    	model.addAttribute("registration",registration);
        return "register_farmer";
    }

    @PostMapping("/register_farmer")
    public String registerFarmer(@ModelAttribute("registration") FarmerRegistrationDTO registration,RedirectAttributes redirectAttributes,Model model) {
        try {
        	
        	 User user = registration.getUser();
             Farmer farmer = registration.getFarmer();
            logger.info("Inside registerFarmer method");
            logger.info("Received farmer data: {}", farmer);
            logger.info("Received user data: {}", user);
            
           Integer farmerId = farmerService.registerFarmer(farmer, user);
           redirectAttributes.addFlashAttribute("farmerId", farmerId);
           return "redirect:/registration_success";
        } catch (EmailAlreadyRegisteredException e) {
            model.addAttribute("errorMessage", "Email is already registered.");
            return "register_farmer"; 
        } catch (UsernameAlreadyRegisteredException e) {
            model.addAttribute("errorMessage", "Username is already taken.");
            return "register_farmer"; 
        } catch (PhoneAlreadyRegisteredException e) {
            model.addAttribute("errorMessage", "Phone number is already registered.");
            return "register_farmer";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
            return "error"; 
        }
    }
    
    @GetMapping("/farmerportaldash")
    public String FarmerPortalDash() {
    	return "farmerportaldash";
    }
}
