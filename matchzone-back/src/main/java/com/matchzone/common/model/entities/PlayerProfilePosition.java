package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa la asignación de una posición a un perfil de jugador.
 */
@Entity
@Table(name = "player_profile_position")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerProfilePosition {

	@EmbeddedId
	private PlayerProfilePositionId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("playerProfileId")
	private PlayerProfile playerProfile;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("positionId")
	private Position position;
}