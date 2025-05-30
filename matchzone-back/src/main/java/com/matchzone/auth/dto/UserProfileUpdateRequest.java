package com.matchzone.auth.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileUpdateRequest {
	
    @NotBlank
    private String usernameVisible;
    private LocalDate birthDate;
    private Long cityId;
    private Long genderId;
    private String profileImageUrl;
}