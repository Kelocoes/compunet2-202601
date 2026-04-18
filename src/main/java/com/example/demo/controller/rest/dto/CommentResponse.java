package com.example.demo.controller.rest.dto;

import java.sql.Timestamp;

import com.example.demo.model.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

    // ID del comentario.
    private Long id;

    // Contenido textual del comentario.
    private String content;

    // Fecha de creacion registrada por el servidor.
    private Timestamp createdAt;

    // ID del usuario autor.
    private Long userId;

    // Username del autor.
    private String username;

    // ID del juego comentado.
    private Long gameId;

    // Nombre del juego comentado.
    private String gameName;

    public static CommentResponse fromEntity(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUser().getId(),
                comment.getUser().getUsername(),
                comment.getGame().getId(),
                comment.getGame().getName());
    }
}
