package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Representa una posición deportiva (por ejemplo: Defensa, Delantero, Portero),
 * vinculada a un deporte concreto.
 */
@Entity
@Table(name = "position")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id", nullable = false)
    private Sport sport;

    @Column(nullable = false)
    private boolean active;
}