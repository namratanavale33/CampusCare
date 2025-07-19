package com.example.CampusCare.repository;

import com.example.CampusCare.entity.Complaint;
import com.example.CampusCare.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {
    List<Technician> findByDepartment(String department);
    Technician findByEmail(String email);



    long countByAssigned(boolean assigned);
}
