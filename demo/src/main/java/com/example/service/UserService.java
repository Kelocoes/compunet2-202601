package com.example.service;

import java.util.List;

import com.example.model.User;
import com.example.repository.UserRepository;

public class UserService {
    
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User saveUser(User user) {
        return userRepository.saveUser(user);
    }
}
