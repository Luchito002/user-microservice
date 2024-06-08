package com.zectia.user_microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zectia.user_microservice.model.Friendship;
import com.zectia.user_microservice.model.User;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
  boolean existsBySenderAndReceiver(User sender, User receiver);
}
