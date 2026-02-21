package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface IUserService {
    
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    void deleteById(Long id);
    List<User> findAllPage(int page, int size);
    List<User> findByUsernameContaining(String username);
}
