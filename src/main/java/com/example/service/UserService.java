package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.User;
import com.example.repository.UserRepository;

public class UserService {
    
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean delete(Integer id) {
        return userRepository.delete(id);
    }
}
