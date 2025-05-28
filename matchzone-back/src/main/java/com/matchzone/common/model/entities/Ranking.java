package com.matchzone.common.model.entities;

import com.matchzone.common.model.enums.RankingTargetType;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad: Ranking
 * Representa el ranking general o específico por deporte para usuarios y equipos.
 */
@Entity
@Table(name = "ranking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ranking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sport_id")
	private Sport sport;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;

	private RankingTargetType targetType;
	
	// ID de User o Team, según targetType
	private Long targetId;
	
	// Escala interna por dificultad
	private int difficultyLevel;
	
	private int points;
	private boolean active;
}
