package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
