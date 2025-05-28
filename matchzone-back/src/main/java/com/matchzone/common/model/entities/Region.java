package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad: Region
 * Representa una región geográfica del país.
 */
@Entity
@Table(name = "region")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

}
