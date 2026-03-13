package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Permission;

public interface PermissionService {

    List<Permission> findAll();

    Permission findById(Long id);

    Permission save(Permission permission);

    Permission update(Long id, Permission permission);

    void deleteById(Long id);
}
