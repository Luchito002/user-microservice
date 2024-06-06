package com.zectia.user_microservice.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.zectia.user_microservice.security.JWTAuthenticationFilter;
import com.zectia.user_microservice.security.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final UserDetailsService userDetailsService;
  private final JWTAuthorizationFilter jwtAuthorizationFilter;

  public SecurityConfig(UserDetailsService userDetailsService, JWTAuthorizationFilter jwtAuthorizationFilter) {
    this.userDetailsService = userDetailsService;
    this.jwtAuthorizationFilter = jwtAuthorizationFilter;
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList(
      "http://localhost:8080",
      "http://localhost:8081",
      "http://localhost:5173",
      "https://community-microservice.onrender.com"
    ));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
    configuration.setAllowCredentials(true); // Habilita las credenciales en las solicitudes CORS

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authManager)
      throws Exception {
    JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
    jwtAuthenticationFilter.setAuthenticationManager(authManager);
    jwtAuthenticationFilter.setFilterProcessesUrl("/login");

    return http
        .authorizeHttpRequests(authRequest -> authRequest
            .requestMatchers("/usuarios/registeruser").permitAll()
            .requestMatchers("/usuarios/obtener-usuarios-por-ids").permitAll()
            .requestMatchers("/login").permitAll()
            .anyRequest().authenticated())
        .csrf(csrf -> csrf.disable())
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .addFilter(jwtAuthenticationFilter)
        .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
        .httpBasic()
        .and()
        .sessionManagement(sessionManager -> sessionManager
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
  }

  @Bean
  AuthenticationManager authManager(HttpSecurity http) throws Exception {

    AuthenticationManagerBuilder authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
    authManagerBuilder
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
    return authManagerBuilder.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
