package com.example;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
         message = "Hello World!";
         System.out.println("HelloServlet initialized");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         out.println("<!DOCTYPE html>");
         out.println("""
                    <head>
                        <title>Hello Servlet</title>
                        <link rel="stylesheet" type="text/css" href="css/main.css">
                        <script src="js/main.js"></script>
                    </head>
                 """);
         out.println("<html><body>");
         out.println("<h1>" + message + "</h1>");
         out.println("</body></html>");
    }

    public void destroy() {
        System.out.println("HelloServlet destroyed");
    }
}