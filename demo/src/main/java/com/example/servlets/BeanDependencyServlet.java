package com.example.servlets;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.service.GameService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "beanDependencyServlet", value = "/bean-dependency-servlet")
public class BeanDependencyServlet extends HttpServlet {

    public void init() {
        System.out.println("BeanDependencyServlet initialized");
    }

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

            GameService gameService = (GameService) context.getBean("GameService");
            System.out.println("Available games: " + gameService.getGames());

            response.setContentType("text/html");
            response.getWriter().println("<h1>Available games</h1>");
            for (String game : gameService.getGames()) {
                response.getWriter().println("<p>" + game + "</p>");
            }

            ((ClassPathXmlApplicationContext) context).close();
        }

        public void destroy() {
            System.out.println("BeanDependencyServlet destroyed");
        }
}