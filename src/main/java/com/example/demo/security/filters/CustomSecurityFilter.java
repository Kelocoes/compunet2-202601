package com.example.demo.security.filters;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomSecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        boolean flag = false;
        if (flag) {
            filterChain.doFilter(request, response);
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Esta pagina esta en mantenimiento, no puede acceder");
    }

}
