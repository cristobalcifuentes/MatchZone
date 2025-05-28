package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;

/**
* Entidad: CourtReservation
* Representa una reserva puntual realizada para una cancha, ya sea por un usuario registrado o externo.
*/
@Entity
@Table(name = "court_reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourtReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "court_id")
    private Court court;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservadoPor_id")
    private User reservadoPor;
}
