package com.example.demo.controller.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.GameRequest;
import com.example.demo.dto.GamesResponse;
import com.example.demo.model.Game;
import com.example.demo.model.User;
import com.example.demo.service.GameService;
import com.example.demo.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/public/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<GamesResponse>> findAll(@RequestParam(required = false) String name) {
        List<Game> games = gameService.findAll();
        List<GamesResponse> gamesResponses = games.stream()
                .map(GamesResponse::toDto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(gamesResponses);
    }

    @GetMapping("/{id}") // PathVariable
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Game game = gameService.findById(id);
            GamesResponse gamesResponse = new GamesResponse(
                    game.getId(),
                    game.getName(),
                    game.getDescription());
            return ResponseEntity.status(HttpStatus.OK).body(gamesResponse);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<GamesResponse> create(@RequestBody GameRequest gameRequest) {
        try {
            User userFound = userService.findById(gameRequest.getUserId());
            Game newGame = new Game();
            newGame.setUser(userFound);
            newGame.setName(gameRequest.getName());
            newGame.setDescription(gameRequest.getDescription());
            newGame.setMinPlayers(gameRequest.getMinPlayers());
            newGame.setMaxPlayers(gameRequest.getMaximoJugadores());
            newGame.setCategory(gameRequest.getCategory());
            Game gameSaved = gameService.save(newGame);

            GamesResponse gamesResponse = new GamesResponse(gameSaved.getId(), gameSaved.getName(),
                    gameSaved.getDescription());

            return ResponseEntity.status(HttpStatus.CREATED).body(gamesResponse);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<GamesResponse> update(@PathVariable Long id, @RequestBody GameRequest gameRequest) {
        try {
            User userFound = userService.findById(gameRequest.getUserId());
            Game gameToUpdate = gameService.findById(id);
            gameToUpdate.setUser(userFound);
            gameToUpdate.setName(gameRequest.getName());
            gameToUpdate.setDescription(gameRequest.getDescription());
            gameToUpdate.setMinPlayers(gameRequest.getMinPlayers());
            gameToUpdate.setMaxPlayers(gameRequest.getMaximoJugadores());
            gameToUpdate.setCategory(gameRequest.getCategory());

            Game gameUpdated = gameService.save(gameToUpdate);
            GamesResponse gamesResponse = new GamesResponse(
                    gameUpdated.getId(),
                    gameUpdated.getName(),
                    gameUpdated.getDescription());

            return ResponseEntity.status(HttpStatus.OK).body(gamesResponse);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            gameService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
