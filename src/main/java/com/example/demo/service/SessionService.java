package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Session;

public interface SessionService {

    List<Session> findAll();

    Session findById(Long id);

    Session save(Session session);

    Session update(Long id, Session session);

    void deleteById(Long id);
}
