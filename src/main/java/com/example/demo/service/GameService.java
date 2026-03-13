package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Game;

public interface GameService {

    List<Game> findAll();

    Game findById(Long id);

    Game save(Game game);

    Game update(Long id, Game game);

    void deleteById(Long id);
}
