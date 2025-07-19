package com.example.CampusCare.repository;

import com.example.CampusCare.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
    List<Admin> findAllByEmail(String email);
}
