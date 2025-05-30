package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad que representa la asignación de un deporte a un perfil de jugador.
 */
@Entity
@Table(name = "player_profile_sport")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerProfileSport {

	@EmbeddedId
	private PlayerProfileSportId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("playerProfileId")
	private PlayerProfile playerProfile;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("sportId")
	private Sport sport;
}