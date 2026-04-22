package com.example.demo.controller.rest;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.GamesResponse;
import com.example.demo.model.Game;
import com.example.demo.service.GameService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/public/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

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
}
