package com.matchzone.common.model.entities;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

/**
 * Identificador compuesto para PlayerProfileSport.
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PlayerProfileSportId implements Serializable {
	private Long playerProfileId; 
	private Long sportId;
}