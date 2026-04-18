package com.example.demo.controller.rest.dto;

import com.example.demo.model.Game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameResponse {

    // ID del juego en base de datos.
    private Long id;

    // Nombre del juego.
    private String name;

    // Descripcion del juego.
    private String description;

    // Minimo de jugadores.
    private Integer minPlayers;

    // Maximo de jugadores.
    private Integer maxPlayers;

    // Categoria funcional del juego.
    private String category;

    // ID del usuario creador para no exponer todo User (incluye password_hash).
    private Long userId;

    // Username del creador para mostrar informacion amigable.
    private String username;

    public static GameResponse fromEntity(Game game) {
        return new GameResponse(
                game.getId(),
                game.getName(),
                game.getDescription(),
                game.getMinPlayers(),
                game.getMaxPlayers(),
                game.getCategory(),
                game.getUser().getId(),
                game.getUser().getUsername());
    }
}
