package com.matchzone.profile.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePlayerProfileDto {

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    @NotNull
    @PastOrPresent(message = "dateOfBirth cannot be in the future")
    private LocalDate dateOfBirth;

    @NotNull
    private String gender;

    @NotNull
    private Long cityId;

    @Size(max = 255)
    private String avatarUrl;

    @Size(max = 20)
    private String phoneNumber;

    @Size(max = 5)
    private String preferredLanguage;

    @Size(max = 250)
    private String bio;

    @PositiveOrZero
    private Double height;

    @PositiveOrZero
    private Double weight;

    @NotNull
    private String preferredFoot;

    @NotEmpty
    private Set<Long> positionIds;

    @NotEmpty
    private Set<Long> sportIds;

    @Size(max = 255)
    private String instagramUrl;

    @Size(max = 255)
    private String twitterUrl;

    @Size(max = 255)
    private String facebookUrl;
}