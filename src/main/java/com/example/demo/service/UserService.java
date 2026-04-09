package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User save(User user);

    User update(Long id, User user);

    void deleteById(Long id);
    
    User findByUsername(String usenrame);
}
