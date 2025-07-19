package com.example.CampusCare.service;

import com.example.CampusCare.entity.User;
import com.example.CampusCare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  UserService {

    @Autowired
    private UserRepository userRepository;


    public void saveUser(User user) {
        userRepository.save(user);
    }


    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }



    // Method to count all users
    public long countAllUsers() {
        return userRepository.count();  // This will return the total count of users in the User table
    }
}
