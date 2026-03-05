package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Permission;

public interface IPermissionRepository extends JpaRepository<Permission, Integer> {
    
    List<Permission> findByRolePermissions_Role_Users_Id(Integer userId);
    
}
