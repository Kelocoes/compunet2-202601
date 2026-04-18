package com.example.demo.controller.rest.dto;

import lombok.Data;

@Data
public class GameRequest {

    // Nombre visible del juego en la API.
    private String name;

    // Descripcion corta del juego.
    private String description;

    // Numero minimo de jugadores.
    private Integer minPlayers;

    // Numero maximo de jugadores.
    private Integer maxPlayers;

    // Categoria general del juego.
    private String category;

    // ID del usuario creador (ManyToOne con User).
    private Long userId;
}
