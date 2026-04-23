package com.example.demo.dto;

import lombok.Data;

@Data
public class GameRequest {

    private String name;
    private String description;
    private Integer minPlayers;
    private Integer maximoJugadores;
    private String category;
    private Long userId;
}
