package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;


/**
 * Entidad: Coach
 * Representa un entrenador personal registrado en la plataforma.
 */
@Entity
@Table(name = "coach")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
