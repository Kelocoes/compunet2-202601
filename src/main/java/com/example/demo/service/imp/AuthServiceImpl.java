package com.example.demo.service.imp;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.AuthService;
import com.example.demo.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(String username, String password) {
        // Comparar contraseñas con la guardada en BD
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); // Search user by username
        if (userDetails == null) {
            throw new RuntimeException("User not found");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Construccion al Token JWT
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
        User user = customUserDetails.getUser();
        String token = this.jwtService.generateToken(user, customUserDetails.getAuthorities());
        return new AuthResponse(token);
    }

}
