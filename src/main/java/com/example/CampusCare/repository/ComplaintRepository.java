package com.example.CampusCare.repository;

import com.example.CampusCare.entity.Complaint;
import com.example.CampusCare.entity.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByEmail(String email);
    List<Complaint> findByStatus(String status);
    List<Complaint> findByDepartment(String department);
    List<Complaint> findByStatusAndDepartment(String status, String department);
    // ComplaintRepository.java
    List<Complaint> findByAssignedTechnician_Id(Long technicianId);



    long countByStatus(ComplaintStatus status);
}
