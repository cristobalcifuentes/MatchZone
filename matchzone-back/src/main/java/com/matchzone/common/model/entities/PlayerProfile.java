package com.matchzone.common.model.entities;


import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "player_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerProfile {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToMany
    @JoinTable(
        name = "player_profile_sports",
        joinColumns = @JoinColumn(name = "profile_id"),
        inverseJoinColumns = @JoinColumn(name = "sport_id")
    )
    private Set<Sport> deportes;

    private Double altura;       // en cm
    private Double peso;         // en kg
    private String posicion;     // ej: Defensa, Delantero
    private String piernaHabil;  // Derecha, Izquierda, Ambas
    private String descripcion;  // Descripción libre o bio
}