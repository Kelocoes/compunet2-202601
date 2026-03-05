package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findById(Integer id);
    Optional<Role> findByNameOrDescription(String name, String description);
}
