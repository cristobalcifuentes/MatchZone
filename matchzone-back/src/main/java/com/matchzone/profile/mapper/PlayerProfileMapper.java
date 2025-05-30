package com.matchzone.profile.mapper;

import com.matchzone.common.model.entities.*;
import com.matchzone.common.model.enums.GenderType;
import com.matchzone.common.model.enums.PreferredFootType;
import com.matchzone.profile.dto.PlayerProfileDto;
import com.matchzone.profile.dto.UpdatePlayerProfileDto;
import java.util.stream.Collectors;

/**
 * Mapper manual para convertir entre PlayerProfile y sus DTOs.
 */
public class PlayerProfileMapper {

    public static PlayerProfileDto toDto(PlayerProfile p) {
        return PlayerProfileDto.builder()
            .id(p.getId())
            .firstName(p.getFirstName())
            .lastName(p.getLastName())
            .dateOfBirth(p.getDateOfBirth())
            .gender(p.getGender().name())
            .cityId(p.getCity().getId())
            .cityName(p.getCity().getName())
            .avatarUrl(p.getAvatarUrl())
            .phoneNumber(p.getPhoneNumber())
            .preferredLanguage(p.getPreferredLanguage())
            .bio(p.getBio())
            .height(p.getHeight())
            .weight(p.getWeight())
            .preferredFoot(p.getPreferredFoot().name())
            .positionIds(p.getPositionAssignments().stream()
                .map(a -> a.getPosition().getId()).collect(Collectors.toSet()))
            .positionNames(p.getPositionAssignments().stream()
                .map(a -> a.getPosition().getName()).collect(Collectors.toSet()))
            .sportIds(p.getSportAssignments().stream()
                .map(a -> a.getSport().getId()).collect(Collectors.toSet()))
            .sportNames(p.getSportAssignments().stream()
                .map(a -> a.getSport().getName()).collect(Collectors.toSet()))
            .instagramUrl(p.getInstagramUrl())
            .twitterUrl(p.getTwitterUrl())
            .facebookUrl(p.getFacebookUrl())
            .build();
    }


    public static void updateBasicFields(UpdatePlayerProfileDto dto, PlayerProfile p) {
        p.setFirstName(dto.getFirstName());
        p.setLastName(dto.getLastName());
        p.setDateOfBirth(dto.getDateOfBirth());
        p.setGender(GenderType.valueOf(dto.getGender()));
        p.setAvatarUrl(dto.getAvatarUrl());
        p.setPhoneNumber(dto.getPhoneNumber());
        p.setPreferredLanguage(dto.getPreferredLanguage());
        p.setBio(dto.getBio());
        p.setHeight(dto.getHeight());
        p.setWeight(dto.getWeight());
        p.setPreferredFoot(PreferredFootType.valueOf(dto.getPreferredFoot()));
    }
}