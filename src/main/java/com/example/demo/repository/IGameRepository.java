package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Game;

public interface IGameRepository extends JpaRepository<Game, Integer> {
    
}
