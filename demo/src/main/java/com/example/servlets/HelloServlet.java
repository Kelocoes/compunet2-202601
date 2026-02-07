package com.example.servlets;
import java.io.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.beans.MyFirstBean;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void init() {
        System.out.println("HelloServlet initialized");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        MyFirstBean myFirstBean = (MyFirstBean) context.getBean("myFirstBean");
        System.out.println(myFirstBean.getMensaje());

        response.setContentType("text/html");
        response.getWriter().println("<h1>" + myFirstBean.getMensaje() + "</h1>");

        ((ClassPathXmlApplicationContext) context).close();
    }

    public void destroy() {
        System.out.println("HelloServlet destroyed");
    }
}