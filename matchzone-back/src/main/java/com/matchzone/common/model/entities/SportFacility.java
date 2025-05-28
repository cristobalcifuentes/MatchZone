package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

/**
 * Entidad: SportFacility
 * Representa un complejo deportivo que puede contener múltiples canchas.
 */
@Entity
@Table(name = "sport_facility")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportFacility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "sportFacility", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SportFacilityAdmin> administradores;
}
