package com.zectia.user_microservice.security;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {

    AuthCredentials authCredentials = new AuthCredentials();

    try {

      authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
    } catch (IOException e) {

    }

    UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
        authCredentials.getCorreo(),
        authCredentials.getClave(),
        Collections.emptyList());

    return getAuthenticationManager().authenticate(usernamePAT);
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult) throws IOException, ServletException {

    UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
    String token = TokenUtils.createToken(userDetails.getUsername(), userDetails.getUsername(), userDetails.getId().toString());

    // Create a map to hold the token and write it as JSON
    Map<String, String> tokenMap = new HashMap<>();
    tokenMap.put("token", token);

    //response.addHeader("Authorization", "Bearer " + token);
    //response.getWriter().flush();
    //super.successfulAuthentication(request, response, chain, authResult);

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(new ObjectMapper().writeValueAsString(tokenMap));
    response.getWriter().flush();

  }

}
