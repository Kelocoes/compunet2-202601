package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
