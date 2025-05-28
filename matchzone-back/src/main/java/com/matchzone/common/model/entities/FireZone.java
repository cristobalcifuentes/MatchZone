package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad: FireZone
 * Representa una solicitud activa de un equipo buscando rival para jugar.
 */
@Entity
@Table(name = "fire_zone")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FireZone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id")
    private Sport sport;
}
