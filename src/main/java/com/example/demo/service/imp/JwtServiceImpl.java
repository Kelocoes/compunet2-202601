package com.example.demo.service.imp;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.service.JwtService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${app.jwt.secret}")
    private String secretKey;

    private SecretKey getSign() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String generateToken(User user, Collection<? extends GrantedAuthority> authorities) {
        return Jwts.builder()
                .id(user.getId().toString())
                .claims(Map.of(
                        "username", user.getUsername(),
                        "email", user.getEmail(),
                        "permissions", authorities.stream().map(ga -> ga.getAuthority()).toList()))
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .signWith(getSign())
                .compact();
    }

}
