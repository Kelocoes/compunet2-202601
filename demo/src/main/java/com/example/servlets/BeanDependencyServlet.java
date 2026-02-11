package com.example.servlets;

import java.io.IOException;

import org.springframework.context.ApplicationContext;

import com.example.context.ContextSingleton;
import com.example.model.User;
import com.example.service.GameService;
import com.example.service.UserService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "beanDependencyServlet", value = "/bean-dependency-servlet")
public class BeanDependencyServlet extends HttpServlet {

    private ApplicationContext context;

    public void init() {
        this.context = ContextSingleton.getInstance().getContext();
        System.out.println("BeanDependencyServlet initialized");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GameService gameService = (GameService) context.getBean("GameService");
        UserService userService = (UserService) context.getBean("UserService");

        response.setContentType("text/html");
        response.getWriter().println("<h1>Available games</h1>");
        for (String game : gameService.getGames()) {
            response.getWriter().println("<p>" + game + "</p>");
        }

        response.getWriter().println("<h2>Registered users</h2>");
        for (User u : userService.getUsers()) {
            response.getWriter().println("<p>" + u.getId() + " - " + u.getUsername() + " - " + u.getName() + " " + u.getLastname() + " (" + u.getAge() + ")</p>");
        }

        response.getWriter().println("<form method=\"post\" action=\"bean-dependency-servlet\">");
        response.getWriter().println("<button type=\"submit\">Save user</button>");
        response.getWriter().println("</form>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserService userService = (UserService) context.getBean("UserService");

        User user = new User();
        user.setUsername("newuser");
        user.setName("New");
        user.setLastname("User");
        user.setAge(25);

        User saved = userService.saveUser(user);
        System.out.println("User saved: " + saved.getUsername());

        response.sendRedirect("bean-dependency-servlet");
    }

    public void destroy() {
        ContextSingleton.getInstance().closeContext();
        System.out.println("BeanDependencyServlet destroyed");
    }
}