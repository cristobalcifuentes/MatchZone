package com.matchzone.common.model.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad: CourtTimeSlot
 * Representa una franja horaria recurrente en que una cancha está disponible.
 */
@Entity
@Table(name = "court_time_slot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourtTimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "court_id")
    private Court court;
    
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean available;
}
