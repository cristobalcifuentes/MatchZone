package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad: Challenge
 * Representa un reto enviado de un equipo a otro para agendar un partido.
 */
@Entity
@Table(name = "challenge")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamDesafiante_id")
    private Team teamDesafiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamDesafiado_id")
    private Team teamDesafiado;
}
