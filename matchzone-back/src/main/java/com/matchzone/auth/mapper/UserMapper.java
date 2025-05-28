package com.matchzone.auth.mapper;

import com.matchzone.auth.dto.RegisterRequest;
import com.matchzone.common.model.entities.User;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class UserMapper {

    public static User toEntity(RegisterRequest request, String hashedPassword) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(hashedPassword)
                .fechaNacimiento(request.getFechaNacimiento())
                .createdAt(LocalDateTime.now())
                .build();
    }
}