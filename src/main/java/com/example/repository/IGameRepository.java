package com.example.repository;

import java.util.List;
import java.util.Optional;

import com.example.model.Games;

public interface IGameRepository {
    
    List<Games> findAll();
    
    Optional<Games> findById(Integer id);
    
    Games save(Games game);
    
    boolean delete(Integer id);
    
}
