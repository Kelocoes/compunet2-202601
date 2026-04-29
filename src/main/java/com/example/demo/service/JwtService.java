package com.example.demo.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.example.demo.model.User;

public interface JwtService {

    String generateToken(User user, Collection<? extends GrantedAuthority> authorities);

}
