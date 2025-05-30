package com.matchzone.common.model.entities;

import com.matchzone.common.model.enums.GenderType;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private GenderType code;

    @Column(nullable = false)
    private String label;
}
