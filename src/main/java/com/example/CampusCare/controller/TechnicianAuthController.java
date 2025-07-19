package com.example.CampusCare.controller;

import com.example.CampusCare.entity.Technician;
import com.example.CampusCare.service.TechnicianService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/technician")
public class TechnicianAuthController {

    @Autowired
    private TechnicianService technicianService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "technician/technician_login";  // Updated to match the new folder name
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        Technician technician = technicianService.getTechnicianByEmail(email);

        if (technician != null && technician.getPassword().equals(password)) {
            session.setAttribute("technicianEmail", technician.getEmail());
            return "redirect:/technician/dashboard";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "technician/technician_login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clear session
        return "redirect:/technician/login"; // Redirect to login page
    }

}
