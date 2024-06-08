package com.zectia.user_microservice.service;

public interface FriendshipService {
  public String requestFriendship(Long userSenderId, Long userReceiverId);
}
