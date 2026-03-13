package com.example.demo.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Participant;
import com.example.demo.repository.ParticipantRepository;
import com.example.demo.service.ParticipantService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantServiceImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public List<Participant> findAll() {
        return participantRepository.findAll();
    }

    @Override
    public Participant findById(Long id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Participant not found with id: " + id));
    }

    @Override
    public Participant save(Participant participant) {
        return participantRepository.save(participant);
    }

    @Override
    public Participant update(Long id, Participant participant) {
        findById(id);
        participant.setId(id);
        return participantRepository.save(participant);
    }

    @Override
    public void deleteById(Long id) {
        Participant participant = findById(id);
        participantRepository.delete(participant);
    }
}
