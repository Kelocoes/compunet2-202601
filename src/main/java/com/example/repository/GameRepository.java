package com.example.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.example.model.Games;

public class GameRepository {
    
    private List<Games> games = new ArrayList<Games>();
    private int nextId = 1;
    private Logger logger = Logger.getLogger(GameRepository.class.getName());

    public void init() {
        logger.info("GameRepository initialized");
        logger.info("Connecting to the game database...");

        save(new Games("Poker", "Juego de cartas clasico", 2, 10, "Cartas"));
        save(new Games("Chess", "Ajedrez clasico", 2, 2, "Estrategia"));
        save(new Games("Monopoly", "Compra y venta de propiedades", 2, 8, "Mesa"));
    }

    public List<Games> findAll() {
        return new ArrayList<Games>(games);
    }

    public Optional<Games> findById(Integer id) {
        if (id == null) {
            return Optional.empty();
        }

        for (Games game : games) {
            if (id.equals(game.getId())) {
                return Optional.of(game);
            }
        }

        return Optional.empty();
    }

    public Games save(Games game) {
        if (game == null) {
            return null;
        }

        if (game.getId() == null) {
            game.setId(nextId++);
            games.add(game);
            return game;
        }

        for (int i = 0; i < games.size(); i++) {
            if (game.getId().equals(games.get(i).getId())) {
                games.set(i, game);
                return game;
            }
        }

        games.add(game);
        return game;
    }

    public boolean delete(Integer id) {
        if (id == null) {
            return false;
        }

        for (int i = 0; i < games.size(); i++) {
            if (id.equals(games.get(i).getId())) {
                games.remove(i);
                return true;
            }
        }

        return false;
    }

    public void destroy() {
        logger.info("GameRepository destroyed");
        logger.info("Disconnecting from the game database...");
        games.clear();
        nextId = 1;
    }
}