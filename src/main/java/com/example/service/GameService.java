package com.example.service;

import com.example.model.Games;
import com.example.repository.GameRepository;
import java.util.List;

public class GameService {
    
    private GameRepository gameRepository;

    // public GameService(GameRepository gameRepository) {
    //     this.gameRepository = gameRepository;
    // }

    public GameService() {
    }

    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Games> findAll() {
        return gameRepository.findAll();
    }

    public Games findById(Integer id) {
        return gameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Game not found with id: " + id));
    }

    public Games save(Games game) {
        return gameRepository.save(game);
    }

    public boolean delete(Integer id) {
        return gameRepository.delete(id);
    }
}