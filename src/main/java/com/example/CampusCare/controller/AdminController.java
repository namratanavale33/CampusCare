package com.example.CampusCare.controller;

import com.example.CampusCare.entity.Complaint;
import com.example.CampusCare.entity.Technician;
import com.example.CampusCare.repository.TechnicianRepository;
import com.example.CampusCare.service.ComplaintService;
import com.example.CampusCare.service.TechnicianService;
import com.example.CampusCare.service.UserService;  // Assuming you have a UserService to manage users
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.CampusCare.entity.ComplaintStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ComplaintService complaintService;
    private final TechnicianService technicianService;
    private final UserService userService;  // Assuming you have a UserService to manage users

    @Autowired
    public AdminController(ComplaintService complaintService, TechnicianService technicianService, UserService userService) {
        this.complaintService = complaintService;
        this.technicianService = technicianService;
        this.userService = userService;
    }

    // =================== Admin Dashboard ===================
    @GetMapping("/dashboard")
    public String showAdminDashboard(Model model) {
        long totalUsers = userService.countAllUsers();  // Get total users
        long pendingComplaints = complaintService.countPendingComplaints();  // Get pending complaints


        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("pendingComplaints", pendingComplaints);


        return "admin/admin_dashboard";
    }

    // =================== Complaint Management ===================
    @GetMapping("/complaints")
    public String viewAllComplaints(Model model) {
        List<Complaint> complaints = complaintService.getAllComplaints();
        model.addAttribute("complaints", complaints);
        return "admin/view_complaints";
    }




    @GetMapping("/complaint/{id}")
    public String viewComplaintDetails(@PathVariable Long id, Model model) {
        Complaint complaint = complaintService.getComplaintById(id);
        model.addAttribute("complaint", complaint);
        return "admin/view_complaint_details";
    }

    @GetMapping("/complaint/{id}/assign")
    public String showAssignPage(@PathVariable Long id, Model model) {
        Complaint complaint = complaintService.getComplaintById(id);
        if (complaint == null || complaint.getDepartment() == null) {
            throw new IllegalArgumentException("Complaint or department not found");
        }

        List<Technician> techs = technicianService.getTechniciansByDepartment(complaint.getDepartment());
        if (techs.isEmpty()) {
            techs = technicianService.getAllTechnicians();
        }

        model.addAttribute("complaint", complaint);
        model.addAttribute("technicians", techs);
        return "admin/assign_technician";
    }

    @PostMapping("/complaint/{id}/assign")
    public String assignTechnician(@PathVariable Long id, @RequestParam Long technicianId) {
        Complaint complaint = complaintService.getComplaintById(id);
        Technician tech = technicianService.getTechnicianById(technicianId);
        complaint.setAssignedTechnician(tech);
        complaintService.saveComplaint(complaint);
        return "redirect:/admin/complaints";
    }

    // =================== Technician Management ===================
    @GetMapping("/add-technician")
    public String showAddTechnicianForm(Model model) {
        model.addAttribute("technician", new Technician());
        return "admin/admin_add_technician";
    }

    @PostMapping("/add-technician")
    public String addTechnician(@ModelAttribute Technician technician, Model model) {
        // Check for duplicate email
        if (technicianService.getTechnicianByEmail(technician.getEmail()) != null) {
            model.addAttribute("error", "Technician with this email already exists.");
            return "admin/admin_add_technician";
        }

        technicianService.saveTechnician(technician);
        model.addAttribute("success", "Technician added successfully!");
        model.addAttribute("technician", new Technician());  // Clear form
        return "admin/admin_add_technician";
    }

    // =================== Technician List ===================
    @Autowired
    private TechnicianRepository technicianRepository;

    @GetMapping("/technicians-list")
    public String showTechniciansList(Model model) {
        // Fetch all technicians from the database
        Iterable<Technician> technicians = technicianRepository.findAll();
        model.addAttribute("technicians", technicians);
        return "admin/technicians_list"; // Return the technicians list view
    }

    // Add a method to handle technician deletion
    @GetMapping("/technician/{id}/delete")
    public String deleteTechnician(@PathVariable Long id) {
        Technician technician = technicianService.getTechnicianById(id);
        if (technician != null) {
            technicianService.deleteTechnician(technician); // Assuming you have a deleteTechnician method
        }
        return "redirect:/admin/technicians-list"; // Redirect back to the technicians list
    }
}
