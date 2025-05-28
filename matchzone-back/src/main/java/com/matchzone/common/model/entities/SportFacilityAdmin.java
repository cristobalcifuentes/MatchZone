package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad: SportFacilityAdmin
 * Representa la relación entre un usuario con rol de administrador de canchas y un complejo deportivo (SportFacility).
 * Permite establecer una asignación explícita de qué usuarios están autorizados a gestionar cada recinto.
 * Esta relación es de muchos a muchos, representada a través de esta entidad intermedia.
 */
@Entity
@Table(name = "sport_facility_admin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportFacilityAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_facility_id")
    private SportFacility sportFacility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
