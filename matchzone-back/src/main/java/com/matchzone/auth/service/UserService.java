package com.matchzone.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.matchzone.auth.dto.RegisterRequest;
import com.matchzone.auth.dto.UserProfileResponse;
import com.matchzone.auth.dto.UserProfileUpdateRequest;
import com.matchzone.auth.mapper.UserMapper;
import com.matchzone.common.exception.ResourceNotFoundException;
import com.matchzone.common.model.entities.City;
import com.matchzone.common.model.entities.Gender;
import com.matchzone.common.model.entities.User;
import com.matchzone.common.model.repository.CityRepository;
import com.matchzone.common.model.repository.GenderRepository;
import com.matchzone.common.model.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GenderRepository genderRepository;
    private final CityRepository cityRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(RegisterRequest request) {
        String usernameVisible  = request.getUsernameVisible();
        String usernameInternal = generateUsernameInternal(usernameVisible);

        Gender gender = genderRepository.findById(request.getGenderId())
            .orElseThrow(() -> new ResourceNotFoundException("Gender not found"));
        City city = cityRepository.findById(request.getCityId())
            .orElseThrow(() -> new ResourceNotFoundException("City not found"));

        User user = User.builder()
            .usernameVisible(usernameVisible)
            .usernameInternal(usernameInternal)
            .email(request.getEmail())
            .passwordHash(passwordEncoder.encode(request.getPassword()))
            .birthDate(request.getBirthDate())
            .gender(gender)
            .city(city)
            .active(true)
            .emailVerified(false)
            .build();

        return userRepository.save(user);
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

    
    public UserProfileResponse updateMyProfile(Long userId, UserProfileUpdateRequest request) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Gender gender = genderRepository.findById(request.getGenderId())
            .orElseThrow(() -> new ResourceNotFoundException("Gender not found"));

        City city = cityRepository.findById(request.getCityId())
        	    .orElseThrow(() -> new ResourceNotFoundException("City not found"));

        UserMapper.updateUserFromRequest(user, request, gender, city);

        return UserMapper.toProfileResponse(user);
    }
}