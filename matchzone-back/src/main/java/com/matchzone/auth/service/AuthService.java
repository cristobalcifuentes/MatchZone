package com.matchzone.auth.service;

import com.matchzone.auth.dto.AuthResponse;
import com.matchzone.auth.dto.LoginRequest;
import com.matchzone.auth.dto.RegisterRequest;
import com.matchzone.common.exception.ResourceNotFoundException;
import com.matchzone.common.model.entities.City;
import com.matchzone.common.model.entities.Gender;
import com.matchzone.common.model.entities.User;
import com.matchzone.common.model.repository.CityRepository;
import com.matchzone.common.model.repository.GenderRepository;
import com.matchzone.common.model.repository.UserRepository;
import com.matchzone.auth.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final GenderRepository genderRepository;
    private final CityRepository cityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    private final EmailService emailService;

    public AuthResponse register(RegisterRequest request) {
    	
    	if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }

        // 1. Generar usernameVisible e interno
        String usernameVisible  = request.getUsernameVisible();
        String usernameInternal = generateUsernameInternal(usernameVisible);

        // 2. Resolver Gender y City por ID
        Gender gender = genderRepository.findById(request.getGenderId())
            .orElseThrow(() -> new ResourceNotFoundException("Género no encontrado"));
        City city = cityRepository.findById(request.getCityId())
            .orElseThrow(() -> new ResourceNotFoundException("Ciudad no encontrada"));

        // 3. Construir y guardar entidad User
        User user = User.builder()
            .usernameVisible(usernameVisible)
            .usernameInternal(usernameInternal)
            .email(request.getEmail())
            .passwordHash(passwordEncoder.encode(request.getPassword()))
            .birthDate(request.getBirthDate())
            .gender(gender)
            .city(city)
            .emailVerified(false)
            .active(true)
            .build();

        user = userRepository.save(user);

        // 4. Enviar email de verificación y generar token de sesión
        String verificationToken = jwtUtil.generateEmailVerificationToken(user);
        emailService.sendVerificationEmail(user.getEmail(), verificationToken);

        String sessionToken = jwtUtil.generateToken(user);
        return new AuthResponse(sessionToken, user.getUsernameVisible(), user.getEmail());
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

        String sessionToken = jwtUtil.generateToken(user);
        return new AuthResponse(sessionToken, user.getUsernameVisible(), user.getEmail());
    }

    public void verifyEmail(String token) {
        String email = jwtUtil.extractEmailFromVerificationToken(token);
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("Token inválido"));

        user.setEmailVerified(true);
        userRepository.save(user);
    }

    public void sendPasswordResetToken(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            String resetToken = jwtUtil.generatePasswordResetToken(user);
            emailService.sendPasswordResetEmail(user.getEmail(), resetToken);
        });
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

        user.setPasswordHash(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    private String generateUsernameInternal(String visible) {
        int count = userRepository.countByUsernameVisible(visible);
        int suffix = count + 1;
        String candidate;
        do {
            candidate = String.format("%s#%03d", visible, suffix++);
        } while (userRepository.existsByUsernameInternal(candidate));
        return candidate;
    }
    
    public AuthResponse getCurrentUser(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(
            token,
            user.getUsernameVisible(),
            user.getEmail()
        );
    }
}
