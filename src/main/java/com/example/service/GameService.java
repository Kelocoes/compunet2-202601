package com.example.service;

import com.example.model.Games;
import com.example.repository.IGameRepository;
import com.example.repository.impl.GameRepository1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

public class GameService {
    
    private IGameRepository gameRepository;


    public GameService(IGameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // @Autowired
    // public void setGameRepository(GameRepository gameRepository) {
    //     this.gameRepository = gameRepository;
    // }

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