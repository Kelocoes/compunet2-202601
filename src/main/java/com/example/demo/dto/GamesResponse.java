package com.example.demo.dto;

import com.example.demo.model.Game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamesResponse {

    private Long id;
    private String name;
    private String description;

    public static GamesResponse toDto(Game game) {
        return new GamesResponse(
                game.getId(),
                game.getName(),
                game.getDescription());
    }
}
