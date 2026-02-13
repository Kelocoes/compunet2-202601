package com.example;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.beans.MyFirstBean;
import com.example.config.AppConfig;
import com.example.service.GameService;
import com.example.service.UserService;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        MyFirstBean myFirstBean = (MyFirstBean) context.getBean("myFirstBean");
        System.out.println(myFirstBean.getMensaje());

        GameService gameService = (GameService) context.getBean("gameService");
        System.out.println("Available games: " + gameService.findAll());

        GameService gameService1 = (GameService) context.getBean("gameService");
        
        System.out.println("Are gameService1 and gameService2 different instances? " + (gameService != gameService1));

        UserService userService = (UserService) context.getBean("userService");
        System.out.println("Users: " + userService.findAll());

        context.close();
    }
}