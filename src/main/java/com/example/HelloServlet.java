 package com.example;

import java.io.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", value = "/compu-2")
public class HelloServlet extends HttpServlet{
    
    public void init() {
        System.out.println("Hello Servlet initialized");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("""
                <h1>Hola! Esto es un servlet en Java</h1>
                """);
    }   

    public void destroy() {
        System.out.println("Hello Servlet destroyed");
    }
}
