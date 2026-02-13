package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.example.repository.impl.GameRepository1;
import com.example.repository.impl.UserRepository;

@Configuration
@ComponentScan(basePackages = "com.example")
@PropertySource("classpath:application.properties")
public class AppConfig {
    

    // @Bean(name = "userRepository")
    // @Scope("singleton")
    // public UserRepository userRepository() {
    //     return new UserRepository();
    // }

    // @Bean(name = "gameRepository", initMethod = "init", destroyMethod = "destroy")
    // @Scope("singleton")
    // public GameRepository gameRepository() {
    //     return new GameRepository();
    // }

    // Wiring can be done via constructor injection or setter injection
    // @Bean(name = "userService")
    // @Scope("singleton")
    // public UserService userService(UserRepository userRepository) {
    //     return new UserService(userRepository);
    // }
}