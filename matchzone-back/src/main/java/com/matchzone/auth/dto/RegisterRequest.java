package com.matchzone.auth.dto;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
public class RegisterRequest {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String usernameVisible;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must have at least 6 characters")
    private String password;
    
    @NotBlank(message = "Password confirmation is required")
    @Size(min = 6, max = 100)
    private String confirmPassword;

    private LocalDate birthDate;

    @NotNull(message = "Gender ID is required")
    private Long genderId;

    @NotNull(message = "City ID is required")
    private Long cityId;
}