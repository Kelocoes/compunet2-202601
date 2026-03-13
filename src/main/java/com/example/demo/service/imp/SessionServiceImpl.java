package com.example.demo.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Session;
import com.example.demo.repository.SessionRepository;
import com.example.demo.service.SessionService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    @Override
    public Session findById(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Session not found with id: " + id));
    }

    @Override
    public Session save(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public Session update(Long id, Session session) {
        findById(id);
        session.setId(id);
        return sessionRepository.save(session);
    }

    @Override
    public void deleteById(Long id) {
        Session session = findById(id);
        sessionRepository.delete(session);
    }
}
