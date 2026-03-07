package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.RolePermission;
import com.example.demo.service.IRolePermissionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/role-permissions")
@RequiredArgsConstructor
public class RolePermissionController {

    private final IRolePermissionService rolePermissionService;

    @GetMapping("/assign-roles")
    public List<RolePermission> assignPermissionToRole() {
        Long roleId = 1L;
        List<Long> permissionIds = List.of(1L, 2L, 3L, 4L, 20L, 40L, 41L);
        List<RolePermission> assignedPermissions = rolePermissionService.assignPermissionToRole(roleId, permissionIds);
        return assignedPermissions;
    }
}
