package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad: UserConnection
 * Representa la conexión de un usuario con una red social externa (Google, Facebook, etc.).
 */
@Entity
@Table(name = "user_connection")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
