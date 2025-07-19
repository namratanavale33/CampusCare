package com.example.CampusCare.service;

import com.example.CampusCare.entity.Technician;
import com.example.CampusCare.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicianService {

    @Autowired
    private final TechnicianRepository technicianRepository;

    public TechnicianService(TechnicianRepository technicianRepository) {
        this.technicianRepository = technicianRepository;
    }

    public void saveTechnician(Technician technician) {
        technicianRepository.save(technician);
    }

    public List<Technician> getAllTechnicians() {
        return technicianRepository.findAll();
    }

    public Technician getTechnicianById(Long id) {
        return technicianRepository.findById(id).orElse(null);
    }

    public List<Technician> getByDepartment(String department) {
        return technicianRepository.findByDepartment(department);
    }

    public List<Technician> getTechniciansByDepartment(String department) {
        return technicianRepository.findByDepartment(department);
    }


    public Technician getTechnicianByEmail(String email) {
        return technicianRepository.findByEmail(email);
    }





    // Method to count assigned technicians
    public long countAssignedTechnicians() {
        return technicianRepository.countByAssigned(true);  // Assuming there's a field "assigned" in the Technician entity
    }

    // You can create a method to count total technicians if needed
    public long countTotalTechnicians() {
        return technicianRepository.count();  // Counts all technicians
    }


    public void deleteTechnician(Technician technician) {
        technicianRepository.delete(technician);
    }
}
