package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad: Club
 * Representa una organización deportiva formal, como una institución o agrupación, que puede tener múltiples equipos.
 * Sirve para agrupar y administrar varios equipos bajo una misma entidad organizativa.
 */
@Entity
@Table(name = "club")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
