package com.zectia.user_microservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "amistades")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
