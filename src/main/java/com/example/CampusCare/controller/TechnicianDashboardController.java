package com.example.CampusCare.controller;

import com.example.CampusCare.entity.Complaint;
import com.example.CampusCare.entity.ComplaintStatus;
import com.example.CampusCare.entity.Technician;
import com.example.CampusCare.service.ComplaintService;
import com.example.CampusCare.service.TechnicianService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/technician")
public class TechnicianDashboardController {

    private final ComplaintService complaintService;
    private final TechnicianService technicianService;

    public TechnicianDashboardController(ComplaintService complaintService, TechnicianService technicianService) {
        this.complaintService = complaintService;
        this.technicianService = technicianService;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        String email = (String) session.getAttribute("technicianEmail");
        if (email == null) return "redirect:/technician/login";

        Technician technician = technicianService.getTechnicianByEmail(email);
        List<Complaint> complaints = complaintService.getComplaintsByTechnicianId(technician.getId());

        model.addAttribute("technician", technician);
        model.addAttribute("complaints", complaints);
        return "technician/technician_dashboard"; // Correct path
    }

    @PostMapping("/complaint/{id}/update-status")
    public String updateStatus(@PathVariable Long id, @RequestParam String status) {
        Complaint complaint = complaintService.getComplaintById(id);

        try {
            ComplaintStatus newStatus = ComplaintStatus.valueOf(status.toUpperCase());
            complaint.setStatus(newStatus);
            complaintService.saveComplaint(complaint);
            return "redirect:/technician/dashboard"; // Simplified redirection
        } catch (IllegalArgumentException e) {
            return "redirect:/technician/dashboard?error=InvalidStatus"; // Handle error with query parameter
        }
    }
}
