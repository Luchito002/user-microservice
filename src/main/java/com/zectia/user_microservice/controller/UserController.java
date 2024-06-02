package com.zectia.user_microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zectia.user_microservice.dto.UserDto;
import com.zectia.user_microservice.service.UserService;


@RestController
@RequestMapping("/usuarios")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("{id}")
  public UserDto findById(@PathVariable Long id) {
    return this.userService.findById(id);
  }

  @PostMapping
  public UserDto insertUser(@RequestBody UserDto userDto) {
    return this.userService.insertUser(userDto);
  }

  @PutMapping
  public UserDto updateUser(@RequestBody UserDto userDto) {
    return this.userService.updateUser(userDto);
  }

}
