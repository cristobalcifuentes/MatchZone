package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad: Notification
 * Representa una notificación enviada a un usuario por una acción relevante dentro de la plataforma.
 */
@Entity
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
