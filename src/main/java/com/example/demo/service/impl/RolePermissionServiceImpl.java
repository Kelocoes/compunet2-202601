package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Permission;
import com.example.demo.model.Role;
import com.example.demo.model.RolePermission;
import com.example.demo.repository.IPermissionRepository;
import com.example.demo.repository.IRolePermissionRepository;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.service.IRolePermissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolePermissionServiceImpl implements IRolePermissionService {

    private final IRolePermissionRepository rolePermissionRepository;
    private final IRoleRepository roleRepository;
    private final IPermissionRepository permissionRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public List<RolePermission> assignPermissionToRole(Long roleId, List<Long> permissionId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("El rol con ID " + roleId + " no existe"));

        // Por cada permisoId en la lista, asignamos el permiso al rol
        for (Long permId : permissionId) {
            System.out.println("Asignando permiso con ID " + permId + " al rol con ID " + roleId);
            Permission permission = permissionRepository.findById(permId)
                    .orElseThrow(() -> new RuntimeException("El permiso con ID " + permId + " no existe"));
            
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRole(role);
            rolePermission.setPermission(permission);
            rolePermissionRepository.save(rolePermission);
        }

        return rolePermissionRepository.findByRoleId(roleId);
    }
}
