package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad: TrainerSession
 * Representa una sesión de entrenamiento agendada dentro de un plan de entrenamiento.
 */
@Entity
@Table(name = "trainer_session")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainerPlan_id")
    private TrainerPlan trainerPlan;
}
