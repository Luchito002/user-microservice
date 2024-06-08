package com.zectia.user_microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zectia.user_microservice.service.FriendshipService;

@RestController
@RequestMapping("/amistades")
public class FriendshipController {
  private final FriendshipService friendshipService;

  @Autowired
  public FriendshipController(FriendshipService friendshipService) {
    this.friendshipService = friendshipService;
  }

  @PostMapping("/solicitud-de-amistad/{usuarioEmisorId}/{usuarioReceptorId}")
  public String requestFriendship(@PathVariable Long usuarioEmisorId, @PathVariable Long usuarioReceptorId) {
    return this.friendshipService.requestFriendship(usuarioEmisorId, usuarioReceptorId);
  }
}
