package com.matchzone.profile.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerProfileDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private Long cityId;
    private String cityName;
    private String avatarUrl;
    private String phoneNumber;
    private String preferredLanguage;
    private String bio;
    private Double height;
    private Double weight;
    private String preferredFoot;
    private Set<Long> positionIds;
    private Set<String> positionNames;
    private Set<Long> sportIds;
    private Set<String> sportNames;
    private String instagramUrl;
    private String twitterUrl;
    private String facebookUrl;
}