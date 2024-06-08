package com.zectia.user_microservice.service;

import java.util.List;

import com.zectia.user_microservice.dto.UserDto;
import com.zectia.user_microservice.model.User;

public interface UserService {
  UserDto findById(Long id);

  String insertUser(UserDto userDto);

  String updateUser(UserDto userDto);

  String deleteUser(Long id);

  String recoverUser(Long id);

  List<User> getUsers();

  List<User> getUsersById(List<Long> userIds);
}
