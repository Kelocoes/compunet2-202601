package com.example.demo.service;

import com.example.demo.dto.AuthResponse;

public interface AuthService {

    AuthResponse login(String username, String password);
}
