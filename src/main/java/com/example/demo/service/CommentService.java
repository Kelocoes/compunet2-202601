package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Comment;

public interface CommentService {

    List<Comment> findAll();

    List<Comment> findByGameId(Long gameId);

    Comment findById(Long id);

    Comment save(Comment comment, Long userId, Long gameId);

    Comment update(Long id, Comment comment, Long userId, Long gameId);

    void deleteById(Long id);
}
