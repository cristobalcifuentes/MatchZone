package com.matchzone.auth.dto;

import java.time.LocalDate;

import com.matchzone.common.model.entities.City;
import com.matchzone.common.model.entities.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {
    private Long id;
    private String usernameVisible;
    private String email;
    private LocalDate birthDate;
    private City city;
    private String profileImageUrl;
    private Gender gender; 
}