package com.matchzone.common.model.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad: AuditLog
 * Representa una acción registrada en el sistema para efectos de auditoría.
 */
@Entity
@Table(name = "audit_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entity;          // Nombre de la entidad afectada (ej: "User", "Match")
    private String entityId;        // ID de la entidad afectada
    private String action;          // Tipo de acción: CREATE, UPDATE, DELETE, LOGIN, etc.
    private String performedBy;     // ID o nombre del usuario que realizó la acción

    private String details;         // JSON u observaciones adicionales

    private LocalDateTime timestamp; // Fecha y hora exacta del evento

    private String ipAddress;       // IP del cliente (opcional pero útil)
    private String userAgent;       // Navegador o dispositivo (opcional)
}