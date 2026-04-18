package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Game;

public interface GameService {

    List<Game> findAll();

    List<Game> findByUserId(Long userId);

    Game findById(Long id);

    Game save(Game game, Long userId);

    Game update(Long id, Game game, Long userId);

    void deleteById(Long id);
}
