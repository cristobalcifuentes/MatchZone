package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;

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

    // Otros campos específicos de la entidad

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainerPlan_id")
    private TrainerPlan trainerPlan;
}
