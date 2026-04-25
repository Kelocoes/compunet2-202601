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

import com.example.demo.controller.rest.dto.CommentRequest;
import com.example.demo.controller.rest.dto.CommentResponse;
import com.example.demo.mappers.ICommentMapper;
import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;

import lombok.RequiredArgsConstructor;

// @RestController marca esta clase como controlador REST y serializa respuestas en JSON automaticamente.
@RestController
// @RequestMapping define la ruta base para todos los endpoints de comentarios.
@RequestMapping("/rest/comments")
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentService commentService;
    private final ICommentMapper commentMapper;

    // @GetMapping atiende peticiones GET para consultar multiples recursos.
    @GetMapping
    public ResponseEntity<List<CommentResponse>> getAllComments() {
        List<CommentResponse> response = commentMapper.commentsToCommentResponses(commentService.findAll());

        // HttpStatus.OK representa 200 para respuesta correcta en lecturas.
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // @GetMapping con path variable para consultar un recurso especifico.
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getCommentById(
            // @PathVariable captura el id ubicado en la URL /rest/comments/{id}.
            @PathVariable Long id) {
        Comment comment = commentService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(commentMapper.commentToCommentResponse(comment));
    }

    // Endpoint REST para consultar comentarios de un juego especifico.
    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByGame(
            // @PathVariable toma gameId desde la URL /rest/comments/game/{gameId}.
            @PathVariable Long gameId) {
        List<CommentResponse> response = commentMapper.commentsToCommentResponses(commentService.findByGameId(gameId));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // @PostMapping atiende peticiones POST para crear nuevos comentarios.
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            // @RequestBody transforma el JSON de entrada en un DTO Java.
            @RequestBody CommentRequest request) {
        Comment comment = commentMapper.commentRequestToComment(request);

        Comment created = commentService.save(comment, request.getUserId(), request.getGameId());

        // HttpStatus.CREATED representa 201 para creacion exitosa.
        return ResponseEntity.status(HttpStatus.CREATED).body(commentMapper.commentToCommentResponse(created));
    }

    // @PutMapping atiende peticiones PUT para reemplazar/actualizar un comentario
    // existente.
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(
            // @PathVariable indica que comentario se actualiza.
            @PathVariable Long id,
            // @RequestBody trae el nuevo estado del comentario en JSON.
            @RequestBody CommentRequest request) {
        Comment existing = commentService.findById(id);

        Comment comment = commentMapper.commentRequestToCommentForUpdate(request);
        comment.setCreatedAt(existing.getCreatedAt());

        Comment updated = commentService.update(id, comment, request.getUserId(), request.getGameId());
        return ResponseEntity.status(HttpStatus.OK).body(commentMapper.commentToCommentResponse(updated));
    }

    // @DeleteMapping atiende peticiones DELETE para eliminar un comentario por id.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(
            // @PathVariable obtiene el identificador desde la ruta.
            @PathVariable Long id) {
        commentService.deleteById(id);

        // HttpStatus.NO_CONTENT representa 204 cuando se elimina correctamente sin
        // cuerpo.
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
