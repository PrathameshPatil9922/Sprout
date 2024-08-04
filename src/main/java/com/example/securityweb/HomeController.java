package com.example.securityweb;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HomeController {

    @GetMapping("/welcome")
    public String getWelcome() {
        return "welcome";
    }
    
    @GetMapping("/home")
    public String gethome() {
        return "index";
    }
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }
    

    @GetMapping("/aboutus")
    public String aboutUs() {
        return "AboutUs";
    }

    @GetMapping("/contactus")
    public String contactUs() {
        return "Contactus";
    }
  
    @GetMapping("/deptdashboard")
    public String deptDashboard() {
        return "deptdashboard";
    }
    
   
    
    
    @GetMapping("/admindash")
    public String adminDash() {
        return "admin";
    }
    
    @GetMapping("/admin")
    public String Admin() {
        return "admin";
    }
    
    @GetMapping("/admin/add_achievements")
    public String Achievements() {
        return "achievements";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
}
