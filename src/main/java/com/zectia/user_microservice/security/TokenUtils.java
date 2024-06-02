package com.zectia.user_microservice.security;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {

  private final static String ACCESS_TOKEN_SECRET = "pUXbc2xRHdP5wrRjUIVzSq9IjjHq6FQjUQEAs7bfaMHmUciUHrZbtJVM36dzM8Tg"; // Secre key
                                                                                                                        // for
                                                                                                                        // signing
                                                                                                                        // the
                                                                                                                        // token
  private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L; // Token validity period in seconds

  public static String createToken(String nombre_usuario, String correo, String id) {
    long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
    Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

    Map<String, Object> extra = new HashMap<>();
    extra.put("nombre_usuario", nombre_usuario);
    extra.put("id_usuario", id);

    SecretKey secretKey = Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes());

    return Jwts.builder()
        .subject(correo)
        .expiration(expirationDate)
        .claims(extra)
        .signWith(secretKey)
        .compact();
  }

  public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
    try {

      SecretKey secretKey = Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes());

      Claims claims = Jwts.parser()
          .verifyWith(secretKey)
          .build()
          .parseSignedClaims(token)
          .getPayload();

      String email = claims.getSubject();

      return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
    } catch (JwtException e) {
      return null;

    }
  }
}
