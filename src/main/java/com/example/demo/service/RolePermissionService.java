package com.example.demo.service;

import java.util.List;

import com.example.demo.model.RolePermission;

public interface RolePermissionService {

    List<RolePermission> findAll();

    RolePermission findById(Long id);

    RolePermission save(RolePermission rolePermission);

    RolePermission update(Long id, RolePermission rolePermission);

    void deleteById(Long id);
}
