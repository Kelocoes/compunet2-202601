package com.example.demo.controller.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.rest.dto.GameRequest;
import com.example.demo.controller.rest.dto.GameResponse;
import com.example.demo.mappers.IGameMapper;
import com.example.demo.model.Game;
import com.example.demo.service.GameService;

import lombok.RequiredArgsConstructor;

// @RestController indica que esta clase expone endpoints REST y que la respuesta se serializa como JSON.
@RestController
// @RequestMapping define el prefijo comun de rutas para todos los metodos de
// este controlador.
@RequestMapping("/rest/games")
@RequiredArgsConstructor
public class GameRestController {

    private final GameService gameService;
    private final IGameMapper gameMapper;

    // @GetMapping mapea peticiones HTTP GET para obtener colecciones de recursos.
    @GetMapping
    public ResponseEntity<List<GameResponse>> getAllGames() {
        // ResponseEntity permite retornar cuerpo + codigo HTTP de forma explicita.
        List<GameResponse> response = gameMapper.gamesToGameResponses(gameService.findAll());

        // HttpStatus.OK corresponde al codigo 200 (consulta exitosa).
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Otro @GetMapping para recurso individual usando variable de ruta.
    @GetMapping("/{id}")
    public ResponseEntity<GameResponse> getGameById(
            // @PathVariable toma el valor de {id} desde la URL y lo asigna al parametro.
            @PathVariable Long id) {
        Game game = gameService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(gameMapper.gameToGameResponse(game));
    }

    // Endpoint REST para consultar juegos por usuario usando una sub-ruta
    // semantica.
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GameResponse>> getGamesByUser(
            // @PathVariable toma el userId desde la URL /rest/games/user/{userId}.
            @PathVariable Long userId) {
        List<GameResponse> response = gameMapper.gamesToGameResponses(gameService.findByUserId(userId));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // @PostMapping mapea peticiones HTTP POST para crear nuevos recursos.
    @PostMapping
    public ResponseEntity<GameResponse> createGame(
            // @RequestBody convierte el JSON del request en un objeto Java (GameRequest).
            @RequestBody GameRequest request) {
        Game game = gameMapper.gameRequestToGame(request);

        Game created = gameService.save(game, request.getUserId());

        // HttpStatus.CREATED corresponde al codigo 201 cuando se crea un recurso.
        return ResponseEntity.status(HttpStatus.CREATED).body(gameMapper.gameToGameResponse(created));
    }

    // @PutMapping mapea peticiones HTTP PUT para actualizar recursos existentes.
    @PutMapping("/{id}")
    public ResponseEntity<GameResponse> updateGame(
            // @PathVariable identifica que recurso se va a actualizar.
            @PathVariable Long id,
            // @RequestBody trae los nuevos datos del recurso en formato JSON.
            @RequestBody GameRequest request) {
        Game game = gameMapper.gameRequestToGame(request);

        Game updated = gameService.update(id, game, request.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(gameMapper.gameToGameResponse(updated));
    }

    // @DeleteMapping mapea peticiones HTTP DELETE para eliminar recursos.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(
            // @PathVariable recibe el id del recurso a eliminar.
            @PathVariable Long id) {
        gameService.deleteById(id);

        // HttpStatus.NO_CONTENT corresponde a 204, exito sin cuerpo de respuesta.
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
