package com.example.CampusCare.controller;

import com.example.CampusCare.entity.Admin;
import com.example.CampusCare.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes("admin")  // Keep admin in session
public class AdminAuthController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/login")
    public String showLoginForm() {
        return "admin/admin_login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               Model model) {
        List<Admin> admins = adminRepository.findAllByEmail(email);

        if (!admins.isEmpty() && admins.get(0).getPassword().equals(password)) {
            Admin admin = admins.get(0);  // Use the first matching admin
            model.addAttribute("admin", admin);
            return "redirect:/admin/dashboard";  // This will now redirect to AdminController's /dashboard method
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "admin/admin_login";
        }
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();  // Clear session attributes (admin)
        return "redirect:/admin/login?logout=true";  // Redirect to login page with a logout message
    }
}


