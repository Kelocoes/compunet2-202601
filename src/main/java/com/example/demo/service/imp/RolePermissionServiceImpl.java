package com.example.demo.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.RolePermission;
import com.example.demo.repository.RolePermissionRepository;
import com.example.demo.service.RolePermissionService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;

    public RolePermissionServiceImpl(RolePermissionRepository rolePermissionRepository) {
        this.rolePermissionRepository = rolePermissionRepository;
    }

    @Override
    public List<RolePermission> findAll() {
        return rolePermissionRepository.findAll();
    }

    @Override
    public RolePermission findById(Long id) {
        return rolePermissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RolePermission not found with id: " + id));
    }

    @Override
    public RolePermission save(RolePermission rolePermission) {
        return rolePermissionRepository.save(rolePermission);
    }

    @Override
    public RolePermission update(Long id, RolePermission rolePermission) {
        findById(id);
        rolePermission.setId(id);
        return rolePermissionRepository.save(rolePermission);
    }

    @Override
    public void deleteById(Long id) {
        RolePermission rolePermission = findById(id);
        rolePermissionRepository.delete(rolePermission);
    }
}
