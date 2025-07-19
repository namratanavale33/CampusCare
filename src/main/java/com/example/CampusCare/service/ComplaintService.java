package com.example.CampusCare.service;

import com.example.CampusCare.entity.Complaint;
import com.example.CampusCare.entity.ComplaintStatus;
import com.example.CampusCare.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private final ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }


    public List<Complaint> getComplaintsByEmail(String email) {
        return complaintRepository.findByEmail(email); // ✅ Correct
    }


    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
    }

    public void saveComplaint(Complaint complaint) {
        complaintRepository.save(complaint);
    }

    public List<Complaint> filterComplaints(String status, String department) {
        if (status != null && department != null) {
            return complaintRepository.findByStatusAndDepartment(status, department);
        } else if (status != null) {
            return complaintRepository.findByStatus(status);
        } else if (department != null) {
            return complaintRepository.findByDepartment(department);
        } else {
            return complaintRepository.findAll();
        }
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();

    }

    public List<Complaint> getComplaintsByTechnicianId(Long technicianId) {
        return complaintRepository.findByAssignedTechnician_Id(technicianId);
    }




    // Method to count pending complaints (where status = "Pending")
    public long countPendingComplaints() {
        return complaintRepository.countByStatus(ComplaintStatus.PENDING);
    }

    // You can also create other methods for different statuses (e.g., "Resolved")
    public long countResolvedComplaints() {
        return complaintRepository.countByStatus(ComplaintStatus.RESOLVED);
    }





}
