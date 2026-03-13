package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Participant;

public interface ParticipantService {

    List<Participant> findAll();

    Participant findById(Long id);

    Participant save(Participant participant);

    Participant update(Long id, Participant participant);

    void deleteById(Long id);
}
