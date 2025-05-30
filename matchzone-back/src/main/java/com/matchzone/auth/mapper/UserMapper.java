package com.matchzone.auth.mapper;

import com.matchzone.auth.dto.RegisterRequest;
import com.matchzone.auth.dto.UserProfileResponse;
import com.matchzone.auth.dto.UserProfileUpdateRequest;
import com.matchzone.common.model.entities.City;
import com.matchzone.common.model.entities.Gender;
import com.matchzone.common.model.entities.User;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class UserMapper {

	public static User toEntity(RegisterRequest request, String hashedPassword, String usernameInternal, Gender gender,
			City city) {
		return User.builder().usernameVisible(request.getUsernameVisible()).usernameInternal(usernameInternal)
				.email(request.getEmail()).passwordHash(hashedPassword).birthDate(request.getBirthDate()).gender(gender)
				.city(city).emailVerified(false).active(true).createdAt(LocalDateTime.now()).build();
	}

	public static UserProfileResponse toProfileResponse(User user) {
		return UserProfileResponse.builder().id(user.getId()).usernameVisible(user.getUsernameVisible())
				.email(user.getEmail()).birthDate(user.getBirthDate()).city(user.getCity())
				.profileImageUrl(user.getProfileImageUrl()).gender(user.getGender()).build();
	}

	public static void updateUserFromRequest(User user, UserProfileUpdateRequest request, Gender gender, City city) {
		user.setUsernameVisible(request.getUsernameVisible());
		user.setBirthDate(request.getBirthDate());
		user.setCity(city);
		user.setProfileImageUrl(request.getProfileImageUrl());
		user.setGender(gender);
	}
}