package com.zectia.user_microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zectia.user_microservice.model.Friendship;
import com.zectia.user_microservice.model.User;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
  boolean existsBySenderAndReceiver(User sender, User receiver);

  List<Friendship> findBySenderIdAndStatus(Long senderId, String status);

  List<Friendship> findByReceiverIdAndStatus(Long senderId, String status);

  List<Friendship> findBySenderIdAndStatusOrReceiverIdAndStatus(Long senderId, String senderStatus, Long receiverId, String receiverStatus);

  Friendship findBySenderIdAndReceiverId(Long senderId, Long receiverId);
}
