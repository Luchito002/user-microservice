package com.zectia.user_microservice.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zectia.user_microservice.model.Friendship;
import com.zectia.user_microservice.model.User;
import com.zectia.user_microservice.repository.FriendshipRepository;
import com.zectia.user_microservice.repository.UserRepository;
import com.zectia.user_microservice.service.FriendshipService;

@Service
public class FriendshipServiceImpl implements FriendshipService {
  private final FriendshipRepository friendshipRepository;
  private final UserRepository userRepository;

  @Autowired
  public FriendshipServiceImpl(FriendshipRepository friendshipRepository, UserRepository userRepository) {
    this.friendshipRepository = friendshipRepository;
    this.userRepository = userRepository;
  }

  @Override
  public String requestFriendship(Long userSenderId, Long userReceiverId) {
    User userSender = userRepository.findById(userSenderId).orElse(null);
    if (userSender == null)
      return "El usuario que envio la solicitud no existe";

    User userReceiver = userRepository.findById(userReceiverId).orElse(null);
    if (userReceiver == null)
      return "El usuario al que enviaste solicitud de amistad no existe";

    boolean existsFriendshipRequest = friendshipRepository.existsBySenderAndReceiver(userSender, userReceiver);

    if(existsFriendshipRequest) return "La solicitud de amistad ya existe";

    Friendship newFriendship = new Friendship(
      userSender,
      userReceiver,
      "pendiente",
      LocalDate.now()
    );

    friendshipRepository.save(newFriendship);

    return "Solicitud enviada con exito";
  }
}
