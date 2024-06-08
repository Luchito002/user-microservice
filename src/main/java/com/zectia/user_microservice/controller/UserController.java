package com.zectia.user_microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zectia.user_microservice.dto.UserDto;
import com.zectia.user_microservice.model.User;
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

  @GetMapping("obtener-usuarios")
  public List<User> getUsers() {
    return this.userService.getUsers();
  }

  @PostMapping("obtener-usuarios-por-ids")
  public List<User> getUsersById(@RequestBody List<Long> userIds) {
    return this.userService.getUsersById(userIds);
  }

  @PostMapping("registeruser")
  public String insertUser(@RequestBody UserDto userDto) {
    return this.userService.insertUser(userDto);
  }

  @PutMapping("editar-usuario")
  public String updateUser(@RequestBody UserDto userDto) {
    return this.userService.updateUser(userDto);
  }

  @PutMapping("eliminar-usuario/{id}")
  public String deleteUser(@PathVariable Long id) {
    return this.userService.deleteUser(id);
  }

  @PutMapping("recoveruser/{id}")
  public String recoverUser(@PathVariable Long id) {
    return this.userService.recoverUser(id);
  }
}
