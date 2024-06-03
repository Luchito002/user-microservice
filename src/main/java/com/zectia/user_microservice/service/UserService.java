package com.zectia.user_microservice.service;

import com.zectia.user_microservice.dto.UserDto;

public interface UserService {
  UserDto findById(Long id);

  UserDto insertUser(UserDto userDto);

  UserDto updateUser(UserDto userDto);

  String deleteUser(Long id);

  String recoverUser(Long id);
}
