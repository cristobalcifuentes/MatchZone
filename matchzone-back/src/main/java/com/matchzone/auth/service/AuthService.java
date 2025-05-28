package com.matchzone.auth.service;


import com.matchzone.auth.dto.LoginRequest;
import com.matchzone.auth.dto.RegisterRequest;
import com.matchzone.auth.dto.AuthResponse;
import com.matchzone.auth.mapper.UserMapper;
import com.matchzone.auth.util.JWTUtil;
import com.matchzone.common.model.entities.User;
import com.matchzone.common.model.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    private final EmailService emailService;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        String hashed = passwordEncoder.encode(request.getPassword());
        User user = UserMapper.toEntity(request, hashed);
        user.setEmailVerified(false);
        User saved = userRepository.save(user);

        String verificationToken = jwtUtil.generateEmailVerificationToken(saved);
        emailService.sendVerificationEmail(saved.getEmail(), verificationToken);

        String token = jwtUtil.generateToken(saved);
        return new AuthResponse(token, saved.getUsername(), saved.getEmail());
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        if (!user.isEmailVerified()) {
            throw new IllegalStateException("Debes verificar tu email antes de iniciar sesión");
        }

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token, user.getUsername(), user.getEmail());
    }

    public void verifyEmail(String token) {
        String email = jwtUtil.extractEmailFromVerificationToken(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Token inválido"));

        user.setEmailVerified(true);
        userRepository.save(user);
    }
    
    public void sendPasswordResetToken(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return; // no revelar si el email existe o no
        }

        User user = optionalUser.get();

        String token = jwtUtil.generatePasswordResetToken(user);
        emailService.sendPasswordResetEmail(user.getEmail(), token);
    }
    
    public void resetPassword(String token, String newPassword) {
        String email = jwtUtil.extractClaim(token, claims -> {
            if (!"password_reset".equals(claims.get("purpose"))) {
                throw new IllegalArgumentException("Token inválido");
            }
            return claims.getSubject();
        });

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        String hashedPassword = passwordEncoder.encode(newPassword);
        user.setPasswordHash(hashedPassword);
        userRepository.save(user);
    }
    
    
}