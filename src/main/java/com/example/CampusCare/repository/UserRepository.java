package com.example.CampusCare.repository;

import com.example.CampusCare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    long count();
}
