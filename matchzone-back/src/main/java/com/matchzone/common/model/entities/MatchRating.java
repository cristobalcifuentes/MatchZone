package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad: MatchRating
 * Representa la calificación o puntuación dada a un partido por parte de un jugador.
 */
@Entity
@Table(name = "match_rating")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
