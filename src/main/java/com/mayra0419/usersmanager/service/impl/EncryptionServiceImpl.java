package com.mayra0419.usersmanager.service.impl;

import com.mayra0419.usersmanager.model.User;
import com.mayra0419.usersmanager.service.EncryptionService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Service
public class EncryptionServiceImpl implements EncryptionService {

    @Value("${encryption.password.token-key}")
    private String jwtTokenKey;

    @Value("${auth.token.duration:5}")
    private int tokenDuration;

    public String cipherPassword(String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(rawPassword);
    }

    public String generateJWT(User user) {
        SecretKey key = Keys.hmacShaKeyFor(jwtTokenKey.getBytes(StandardCharsets.UTF_8));

        ZonedDateTime now = LocalDateTime.now().atZone(ZoneOffset.UTC);

        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .subject(user.getEmail())
                .claim("name", user.getName())
                .claim("email", user.getEmail())
                .issuedAt(new Date())
                .issuedAt(Date.from(now.toInstant()))
                .expiration(Date.from(now.plusMinutes(tokenDuration).toInstant()))
                .signWith(key)
                .compact();
    }
}
