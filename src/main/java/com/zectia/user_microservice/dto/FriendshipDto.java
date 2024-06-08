package com.zectia.user_microservice.dto;

import java.time.LocalDate;

public class FriendshipDto {
  private Long id;
  private Long senderId;
  private Long receiverId;
  private String status;
  private LocalDate sentDate;

  // Default constructor
  public FriendshipDto() {
  }

  // Parameterized constructor
  public FriendshipDto(Long id, Long senderId, Long receiverId, String status, LocalDate sentDate) {
    this.id = id;
    this.senderId = senderId;
    this.receiverId = receiverId;
    this.status = status;
    this.sentDate = sentDate;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getSenderId() {
    return senderId;
  }

  public void setSenderId(Long senderId) {
    this.senderId = senderId;
  }

  public Long getReceiverId() {
    return receiverId;
  }

  public void setReceiverId(Long receiverId) {
    this.receiverId = receiverId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDate getSentDate() {
    return sentDate;
  }

  public void setSentDate(LocalDate sentDate) {
    this.sentDate = sentDate;
  }
}
