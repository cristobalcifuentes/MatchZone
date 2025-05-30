package com.matchzone.profile.controller;

import com.matchzone.profile.dto.PlayerProfileDto;
import com.matchzone.profile.dto.UpdatePlayerProfileDto;
import com.matchzone.profile.service.PlayerProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para gestionar el perfil del jugador autenticado.
 */
@RestController
@RequestMapping("/api/players/me")
@RequiredArgsConstructor
public class PlayerProfileController {

    private final PlayerProfileService profileService;


    @GetMapping
    public ResponseEntity<PlayerProfileDto> getCurrentProfile() {
        return ResponseEntity.ok(profileService.getCurrentPlayerProfile());
    }

    @PutMapping
    public ResponseEntity<PlayerProfileDto> updateProfile(
            @Valid @RequestBody UpdatePlayerProfileDto dto) {
        return ResponseEntity.ok(profileService.updateCurrentPlayerProfile(dto));
    }
}