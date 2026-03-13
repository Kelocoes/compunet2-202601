package com.example.demo.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Permission;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.PermissionService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission findById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found with id: " + id));
    }

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission update(Long id, Permission permission) {
        findById(id);
        permission.setId(id);
        return permissionRepository.save(permission);
    }

    @Override
    public void deleteById(Long id) {
        Permission permission = findById(id);
        permissionRepository.delete(permission);
    }
}
