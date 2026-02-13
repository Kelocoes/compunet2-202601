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

@WebServlet(name = "gamesServlet", value = "/games")
public class GamesServlet extends HttpServlet {

    private ApplicationContext context;

    public void init() {
        this.context = ContextSingleton.getInstance().getContext();
        System.out.println("GamesServlet initialized");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GameService gameService = context.getBean(GameService.class);
        UserService userService = context.getBean(UserService.class);

        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>Available Games</h1>");
        response.getWriter().println("<ul>");
        gameService.findAll().forEach(game -> {
            try {
                response.getWriter().println("<li>" + game.getName() + "</li>");
                response.getWriter().println("<p> Created by user with id:" + game.getCreatedBy() + "</p>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        response.getWriter().println("</ul>");

        response.getWriter().println("<h2>Users</h2>");
        response.getWriter().println("<ul>");
        userService.findAll().forEach(user -> {
            try {
                response.getWriter().println("<li>" + user.getUsername() + "</li>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        response.getWriter().println("</ul>");

        //Assign a user to a game
        response.getWriter().println("<h2>Assign User to Game</h2>");
        response.getWriter().println("<form method='post' action='games'>");
        response.getWriter().println("Game ID: <input type='number' name='gameId'><br>");
        response.getWriter().println("User ID: <input type='number' name='userId'><br>");
        response.getWriter().println("<input type='submit' value='Assign User'>");
        response.getWriter().println("</form>");

        response.getWriter().println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer gameId = Integer.parseInt(request.getParameter("gameId"));
        Integer userId = Integer.parseInt(request.getParameter("userId"));

        GameService gameService = context.getBean(GameService.class);
        UserService userService = context.getBean(UserService.class);

        try {
            com.example.model.Games game = gameService.findById(gameId);
            User user = userService.findById(userId);

            game.setCreatedBy(user.getId());
            gameService.save(game);

            response.sendRedirect("games");
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }

    }

    public void destroy() {
        System.out.println("GamesServlet destroyed");
    }
}