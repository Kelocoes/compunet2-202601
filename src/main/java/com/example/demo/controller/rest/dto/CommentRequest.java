package com.example.demo.controller.rest.dto;

import lombok.Data;

@Data
public class CommentRequest {

    // Texto del comentario publicado por un usuario.
    private String content;

    // ID del usuario autor del comentario.
    private Long userId;

    // ID del juego al que pertenece el comentario.
    private Long gameId;
}
