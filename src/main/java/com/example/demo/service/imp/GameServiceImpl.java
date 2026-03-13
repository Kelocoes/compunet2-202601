package com.example.demo.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Game;
import com.example.demo.repository.GameRepository;
import com.example.demo.service.GameService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game findById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Game not found with id: " + id));
    }

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game update(Long id, Game game) {
        findById(id);
        game.setId(id);
        return gameRepository.save(game);
    }

    @Override
    public void deleteById(Long id) {
        Game game = findById(id);
        gameRepository.delete(game);
    }
}
