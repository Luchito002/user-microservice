package com.zectia.user_microservice.service;

import java.util.List;

import com.zectia.user_microservice.model.Friendship;

public interface FriendshipService {
  public String requestFriendship(Long userSenderId, Long userReceiverId);

  public List<Friendship> getFriendshipRequestsByUserSender(Long userSenderId);

  public List<Friendship> getFriendshipRequestsByUserId(Long userId);

  public List<Friendship> getFriendnshipsByUserId(Long userId);

  public String acceptRequest(Long userSenderId, Long userReceiverId);

  public String rejectRequest(Long userSenderId, Long userReceiverId);
}
