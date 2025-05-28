package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad: ClubTeam
 * Representa la relación explícita entre un club deportivo (Club) y uno de sus equipos (Team).
 * Permite mantener la estructura jerárquica entre clubes y sus equipos asociados.
 */
@Entity
@Table(name = "club_team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClubTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
}
