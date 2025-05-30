package com.matchzone.auth.controller;


import com.matchzone.auth.dto.AuthResponse;
import com.matchzone.auth.dto.ForgotPasswordRequest;
import com.matchzone.auth.dto.LoginRequest;
import com.matchzone.auth.dto.RegisterRequest;
import com.matchzone.auth.dto.ResetPasswordRequest;
import com.matchzone.auth.service.AuthService;
import com.matchzone.auth.util.JWTUtil;
import com.matchzone.common.model.entities.User;
import com.matchzone.common.model.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
        try {
            authService.verifyEmail(token);
            return ResponseEntity.ok("Correo verificado correctamente. Ya puedes iniciar sesión.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Token inválido o expirado.");
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(null);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(403).body(null); 
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        try {
            AuthResponse response = authService.register(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        authService.sendPasswordResetToken(request.getEmail());
        return ResponseEntity.ok("Si el correo está registrado, recibirás un enlace para restablecer tu contraseña.");
    }
    
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        try {
            authService.resetPassword(request.getToken(), request.getNewPassword());
            return ResponseEntity.ok("Tu contraseña ha sido actualizada correctamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Token inválido o expirado.");
        }
    }
    
    @GetMapping("/me")
    public ResponseEntity<AuthResponse> getCurrentUser(Authentication auth) {
        String email = auth.getName();
        AuthResponse response = authService.getCurrentUser(email);
        return ResponseEntity.ok(response);
    }
}