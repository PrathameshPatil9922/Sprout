package com.example.securityweb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.example.securityweb.Entity.Achievement;
import com.example.securityweb.Service.AchievementService;
import com.example.securityweb.Service.FileStorageService;

import java.util.List;

@Controller
public class AchievementController {

    private final AchievementService achievementService;
    private final FileStorageService fileStorageService;
    
    public AchievementController(AchievementService achievementService,FileStorageService fileStorageService) {
        this.achievementService = achievementService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/achievements")
    public String showAchievements(Model model) {
        List<Achievement> achievements = achievementService.getAllAchievements();
        model.addAttribute("achievements", achievements);
        return "achievement";
    }

    @PostMapping("/achievements/add")
    public String addAchievement(@RequestParam("title") String title,
                                 @RequestParam("description") String description,
                                 @RequestParam("image") MultipartFile image) {
        achievementService.addAchievement(title, description, image);
        return "redirect:/success"; // Redirect to success page after submission
    }
}
