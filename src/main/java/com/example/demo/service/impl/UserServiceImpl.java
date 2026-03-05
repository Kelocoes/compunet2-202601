package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.IUserRepository;
import com.example.demo.service.IRoleService;
import com.example.demo.service.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    private final IRoleService roleService;

    @Override
    public List<User> getAllUsers() {
        // TODO: Transformación, ofuscado
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        Role role = roleService.findById(2); 
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public void remove(Integer id) {
        userRepository.deleteById(id);
    }

}
