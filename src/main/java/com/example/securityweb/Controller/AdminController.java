package com.example.securityweb.Controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.securityweb.MyUserService;
import com.example.securityweb.Role;
import com.example.securityweb.RoleRepository;
import com.example.securityweb.User;
import com.example.securityweb.Entity.SugarFactoryData;
import com.example.securityweb.Service.SugarFactoryDataService;



@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
    private MyUserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SugarFactoryDataService service;
    
    @GetMapping("/adminlogin")
    public String showLoginForm() {
        return "adminLogin";
    }
    
    @GetMapping("/admindash")
    public String adminDash() {
        return "admin";
    }
        
    @GetMapping("/admindash/addAdmin")
    public String addAdmin() {
        return "addAdmin";
    }

    @GetMapping("/admindash/events")
    public String addEvent() {
        return "event";
    }
 
    @GetMapping("/analytics")
    public String index(Model model) {
        return " ";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("data", new SugarFactoryData());
        return "analytics_data";
    }

    @PostMapping("/add_data")
    public String addData(SugarFactoryData data) {
        
        service.save(data);
        
        return "redirect:/admin/admindash";
    }
    
    @GetMapping("/admindash/addUser")
    public String addUser() {
    	return "addUser";
    }
    
    @PostMapping("/admindash/addUser")
    public String addUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String role) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); 

        
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(role);
        roles.add(userRole);
        user.setRoles(roles);

        userService.save(user);
        return "redirect:/admindash"; 
    }
}

