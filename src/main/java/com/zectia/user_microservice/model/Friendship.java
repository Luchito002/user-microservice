package com.zectia.user_microservice.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "amistades")
public class Friendship {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "emisor_id", nullable = false)
  private User sender;

  @ManyToOne
  @JoinColumn(name = "receptor_id", nullable = false)
  private User receiver;

  @Column(nullable = false, length = 15)
  private String status;

  @Column(name = "fecha_emision", nullable = false)
  private LocalDate sentDate;

  // Default constructor
  public Friendship() {
  }

  // Parameterized constructor
  public Friendship(User sender, User receiver, String status, LocalDate sentDate) {
    this.sender = sender;
    this.receiver = receiver;
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

  public User getSender() {
    return sender;
  }

  public void setSender(User sender) {
    this.sender = sender;
  }

  public User getReceiver() {
    return receiver;
  }

  public void setReceiver(User receiver) {
    this.receiver = receiver;
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
