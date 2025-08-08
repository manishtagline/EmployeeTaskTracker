package com.example.employeetasktracker.controller;

import lombok.Getter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String dash(){
        return "admin/dashboard";
    }





    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminDashboard(Model model) {
        model.addAttribute("pageTitle", "Admin Control Panel");
        model.addAttribute("totalEmployees", 25);
        model.addAttribute("activeTasks", 45);
        return "admin/dashboard";
    }

    @GetMapping("/user/dashboard")
    @PreAuthorize("hasRole('USER')")
    public String userDashboard(Model model) {
        model.addAttribute("pageTitle", "My Workspace");
        model.addAttribute("userStats", "");
        return "user/dashboard";
    }

}
