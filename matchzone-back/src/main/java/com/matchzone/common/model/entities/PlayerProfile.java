package com.matchzone.common.model.entities;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.matchzone.common.model.enums.GenderType;
import com.matchzone.common.model.enums.PreferredFootType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa el perfil de un jugador, con información personal y deportiva.
 */
@Entity
@Table(name = "player_profile")
@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class PlayerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderType gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "avatar_url", length = 255)
    private String avatarUrl;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "preferred_language", length = 5)
    private String preferredLanguage;

    @Column(length = 250)
    private String bio;

    @Column
    private Double height;

    @Column
    private Double weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "preferred_foot", nullable = false)
    private PreferredFootType preferredFoot;

    @OneToMany(mappedBy = "playerProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<PlayerProfilePosition> positionAssignments = new HashSet<>();

    @OneToMany(mappedBy = "playerProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<PlayerProfileSport> sportAssignments = new HashSet<>();

    @Column(name = "instagram_url", length = 255)
    private String instagramUrl;

    @Column(name = "twitter_url", length = 255)
    private String twitterUrl;

    @Column(name = "facebook_url", length = 255)
    private String facebookUrl;
}