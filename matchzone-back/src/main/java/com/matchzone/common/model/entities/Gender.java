package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad: Gender
 * Representa el género (masculino, femenino, mixto, otro) de usuarios y equipos.
 */
@Entity
@Table(name = "gender")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; 
}
