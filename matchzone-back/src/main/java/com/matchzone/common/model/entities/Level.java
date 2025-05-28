package com.matchzone.common.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad: Level
 * Representa un nivel de progresión alcanzable por un jugador o equipo en función de su puntaje acumulado.
 * Cada nivel define un rango de puntos que determina si un usuario pertenece a ese nivel.
 * Esta entidad permite clasificar a los usuarios en niveles como Principiante, Intermedio, Avanzado, etc.,
 * y se calcula dinámicamente a partir del puntaje registrado en el sistema de ranking.
 */
@Entity
@Table(name = "level")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; 

    private int minPoints;
    private int maxPoints;

    private String description;
    private boolean active;
    
}