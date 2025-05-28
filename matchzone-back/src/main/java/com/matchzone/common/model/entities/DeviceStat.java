package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad: DeviceStat
 * Representa un dato biométrico o métrico capturado desde un dispositivo conectado.
 */
@Entity
@Table(name = "device_stat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userDevice_id")
    private UserDevice userDevice;
}
