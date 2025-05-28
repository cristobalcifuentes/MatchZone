package com.matchzone.common.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_connection")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Otros campos específicos de la entidad

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
