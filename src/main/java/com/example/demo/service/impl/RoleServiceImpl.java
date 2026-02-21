package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.service.IRoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepository roleRepository;

    @Override
    public List<Role> findByRolesWithAtLeast1Username() {
        return roleRepository.findByRolesWithAtLeast1Username();
    }
}
