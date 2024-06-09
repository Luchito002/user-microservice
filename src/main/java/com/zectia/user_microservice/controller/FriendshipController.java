package com.zectia.user_microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zectia.user_microservice.model.Friendship;
import com.zectia.user_microservice.service.FriendshipService;

@RestController
@RequestMapping("amistades")
public class FriendshipController {
  private final FriendshipService friendshipService;

  @Autowired
  public FriendshipController(FriendshipService friendshipService) {
    this.friendshipService = friendshipService;
  }

  @PostMapping("realizar-de-amistad/{usuarioEmisorId}/{usuarioReceptorId}")
  public String requestFriendship(@PathVariable Long usuarioEmisorId, @PathVariable Long usuarioReceptorId) {
    return this.friendshipService.requestFriendship(usuarioEmisorId, usuarioReceptorId);
  }

  @GetMapping("obtener-solicitudes-de-amistad-hechas-por-el-usuario/{usuarioId}")
  public List<Friendship> getFriendshipRequestsByUserSender(@PathVariable Long usuarioId) {
    return this.friendshipService.getFriendshipRequestsByUserSender(usuarioId);
  }

  @GetMapping("obtener-solicitudes-de-amistad-hechas-para-el-usuario/{usuarioId}")
  public List<Friendship> getFriendshipRequestsByUserId(@PathVariable Long usuarioId) {
    return this.friendshipService.getFriendshipRequestsByUserId(usuarioId);
  }

  @GetMapping("obtener-amistades/{usuarioId}")
  public List<Friendship> getFriendnshipsByUserId(@PathVariable Long usuarioId) {
    return this.friendshipService.getFriendnshipsByUserId(usuarioId);
  }

  @PutMapping("aceptar-solicitud-de-amistad/{usuarioEmisorId}/{usuarioReceptorId}")
  public String acceptRequest(@PathVariable Long usuarioEmisorId, @PathVariable Long usuarioReceptorId) {
    return this.friendshipService.acceptRequest(usuarioEmisorId, usuarioReceptorId);
  }

  @PutMapping("rechazar-solicitud-de-amistad/{usuarioEmisorId}/{usuarioReceptorId}")
  public String rejectRequest(@PathVariable Long usuarioEmisorId, @PathVariable Long usuarioReceptorId) {
    return this.friendshipService.rejectRequest(usuarioEmisorId, usuarioReceptorId);
  }
}
