package com.zectia.user_microservice.service.impl;

import java.time.LocalDate;
import java.util.List;

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

    if (existsFriendshipRequest)
      return "La solicitud de amistad ya existe";

    Friendship newFriendship = new Friendship(
        userSender,
        userReceiver,
        "pendiente",
        LocalDate.now());

    friendshipRepository.save(newFriendship);

    return "Solicitud enviada con exito";
  }

  @Override
  public List<Friendship> getFriendshipRequestsByUserSender(Long userSenderId) {
    return friendshipRepository.findBySenderIdAndStatus(userSenderId, "pendiente");
  }

  @Override
  public List<Friendship> getFriendshipRequestsByUserId(Long userReceiverId) {
    return friendshipRepository.findByReceiverIdAndStatus(userReceiverId, "pendiente");
  }

  @Override
  public List<Friendship> getFriendnshipsByUserId(Long userId) {
    return friendshipRepository.findBySenderIdAndStatusOrReceiverIdAndStatus(userId, "aceptado", userId, "aceptado");
  }

  @Override
  public String acceptRequest(Long userSenderId, Long userReceiverId) {

    Friendship friendshipRequest = friendshipRepository.findBySenderIdAndReceiverId(userSenderId, userReceiverId);

    if (friendshipRequest == null) {
      return "No existe solicitud de amistad entre ambos usuarios";
    }

    friendshipRequest.setStatus("aceptado");

    friendshipRepository.save(friendshipRequest);

    return "Solicitud de amistad aceptada";
  }

  @Override
  public String rejectRequest(Long userSenderId, Long userReceiverId) {
    Friendship friendshipRequest = friendshipRepository.findBySenderIdAndReceiverId(userSenderId, userReceiverId);

    if (friendshipRequest == null) {
      return "No existe solicitud de amistad entre ambos usuarios";
    }

    friendshipRequest.setStatus("rechazado");

    friendshipRepository.save(friendshipRequest);

    return "Solicitud de amistad rechazada";
  }
}
