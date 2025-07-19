package com.example.CampusCare.controller;

import com.example.CampusCare.entity.Complaint;
import com.example.CampusCare.entity.ComplaintStatus;
import com.example.CampusCare.repository.ComplaintRepository;
import com.example.CampusCare.service.ComplaintService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final ComplaintService service;
    private final ComplaintRepository complaintRepository;

    @Autowired
    public StudentController(ComplaintService service, ComplaintRepository complaintRepository) {
        this.service = service;
        this.complaintRepository = complaintRepository;
    }

    // ========== Student Dashboard ==========
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userEmail");
        if (email == null) return "redirect:/user/login";

        List<Complaint> complaints = service.getComplaintsByEmail(email);
        int total = complaints.size();
        long resolved = complaints.stream().filter(c -> c.getStatus() == ComplaintStatus.RESOLVED).count();
        long pending = complaints.stream().filter(c -> c.getStatus() == ComplaintStatus.PENDING).count();

        model.addAttribute("email", email);
        model.addAttribute("total", total);
        model.addAttribute("resolved", resolved);
        model.addAttribute("pending", pending);
        return "student/user_dashboard";
    }


    // ========== Complaint Registration Form ==========
    @GetMapping("/register_complaint")
    public String showComplaintForm(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userEmail");
        if (email == null) return "redirect:/user/login";

        Complaint complaint = new Complaint();
        complaint.setEmail(email);  // Pre-fill email
        complaint.setStatus(ComplaintStatus.PENDING);// Default status
        model.addAttribute("complaint", complaint);
        model.addAttribute("email", email);
        return "student/register_complaint";
    }

    // ========== Submit Complaint ==========
    @PostMapping("/register_complaint")
    public String registerComplaint(@ModelAttribute Complaint complaint, HttpSession session) {
        String email = (String) session.getAttribute("userEmail");
        if (email == null) return "redirect:/user/login";

        // Set additional fields
        complaint.setEmail(email);
        complaint.setStatus(ComplaintStatus.PENDING);

        complaint.setSubmissionDate(LocalDateTime.now());  // Automatically capture date/time

        complaintRepository.save(complaint);
        return "redirect:/student/view_complaints";
    }

    // ========== View My Complaints ==========
    @GetMapping("/view_complaints")
    public String viewComplaints(HttpSession session, Model model) {
        String email = (String) session.getAttribute("userEmail");
        if (email == null) return "redirect:/user/login";

        List<Complaint> complaints = service.getComplaintsByEmail(email);
        model.addAttribute("complaints", complaints);
        model.addAttribute("email", email);
        return "student/view_complaints";
    }
}
