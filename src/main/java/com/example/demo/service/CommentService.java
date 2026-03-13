package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Comment;

public interface CommentService {

    List<Comment> findAll();

    Comment findById(Long id);

    Comment save(Comment comment);

    Comment update(Long id, Comment comment);

    void deleteById(Long id);
}
