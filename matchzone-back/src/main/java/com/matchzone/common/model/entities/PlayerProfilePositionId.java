package com.matchzone.common.model.entities;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

/**
 * Identificador compuesto para PlayerProfilePosition.
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PlayerProfilePositionId implements Serializable {
	private Long playerProfileId; // referencia a PlayerProfile.id
	private Long positionId; // referencia a Position.id
}
