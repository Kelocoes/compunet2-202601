package com.example.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.example.repository.IGameRepository;
import com.example.repository.impl.GameRepository1;
import com.example.service.GameService;

@Configuration
@ComponentScan(basePackages = "com.example")
@PropertySource("classpath:application.properties")
public class AppConfig {
    
    @Bean
    public GameService gameService(@Qualifier("gameRepository1") IGameRepository gameRepository) {
        return new GameService(gameRepository);
    }
}
