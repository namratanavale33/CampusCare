package com.example.CampusCare.controller;

import com.example.CampusCare.entity.Complaint;
import com.example.CampusCare.entity.User;
import com.example.CampusCare.service.ComplaintService;
import com.example.CampusCare.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserAuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "student/user_register"; // Updated
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute User user, Model model) {
        if (userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email already exists!");
            return "student/user_register"; // Updated
        }
        userService.saveUser(user);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "student/user_login"; // Updated
    }

    @Autowired
    private ComplaintService complaintService;

    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               Model model,
                               HttpSession session) {
        User user = userService.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("userEmail", email); // ✅ store email in session
            return "redirect:/student/dashboard";     // ✅ no need to pass email in URL
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "student/user_login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login?logout=true";
    }

}
