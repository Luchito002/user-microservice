package com.zectia.user_microservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.zectia.user_microservice.repository.UserRepository;
import com.zectia.user_microservice.model.User;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
    User user = userRepository
      .findByCorreo(correo)
      .orElseThrow(() -> new UsernameNotFoundException("Not found"));

    return new UserDetailsImpl(user);
  }
}
