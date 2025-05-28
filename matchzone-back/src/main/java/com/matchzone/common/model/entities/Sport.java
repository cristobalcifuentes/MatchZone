package com.matchzone.common.model.entities;

import com.matchzone.common.model.enums.CompetitionType;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity: Sport
 * Represents a sport available on the platform (e.g., football, padel, etc.).
 */
@Entity
@Table(name = "sport")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String iconUrl;
    private String description;
    private boolean active;
    private int maxPlayersPerTeam;
    private int minPlayersPerMatch;
    private CompetitionType competitionType;
}