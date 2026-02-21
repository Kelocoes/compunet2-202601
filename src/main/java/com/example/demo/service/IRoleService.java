package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Role;

public interface IRoleService {
    List<Role> findByRolesWithAtLeast1Username();
}
