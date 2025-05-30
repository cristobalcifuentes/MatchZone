package com.matchzone.profile.service.impl;

import com.matchzone.profile.dto.PlayerProfileDto;
import com.matchzone.profile.dto.UpdatePlayerProfileDto;
import com.matchzone.profile.mapper.PlayerProfileMapper;
import com.matchzone.common.model.entities.*;
import com.matchzone.common.model.repository.*;
import com.matchzone.profile.service.PlayerProfileService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
@Transactional
@RequiredArgsConstructor
public class PlayerProfileServiceImpl implements PlayerProfileService {

    private final PlayerProfileRepository profileRepo;
    private final UserRepository userRepo;
    private final CityRepository cityRepo;
    private final PositionRepository positionRepo;
    private final SportRepository sportRepo;

    @Override
    public PlayerProfileDto getCurrentPlayerProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        PlayerProfile profile = profileRepo.findByUserId(user.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil no encontrado"));

        return PlayerProfileMapper.toDto(profile);
    }

    @Override
    @Transactional
    public PlayerProfileDto updateCurrentPlayerProfile(UpdatePlayerProfileDto dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        PlayerProfile profile = profileRepo.findByUserId(user.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Perfil no encontrado"));

        PlayerProfileMapper.updateBasicFields(dto, profile);

        City city = cityRepo.findById(dto.getCityId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ciudad no encontrada"));
        profile.setCity(city);

        profile.getPositionAssignments().clear();
        for (Long posId : dto.getPositionIds()) {
            Position pos = positionRepo.findById(posId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Posición no encontrada"));
            PlayerProfilePositionId pid = new PlayerProfilePositionId(profile.getId(), pos.getId());
            profile.getPositionAssignments().add(
                PlayerProfilePosition.builder()
                    .id(pid)
                    .playerProfile(profile)
                    .position(pos)
                    .build()
            );
        }

        profile.getSportAssignments().clear();
        for (Long spId : dto.getSportIds()) {
            Sport sp = sportRepo.findById(spId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deporte no encontrado"));
            PlayerProfileSportId sid = new PlayerProfileSportId(profile.getId(), sp.getId());
            profile.getSportAssignments().add(
                PlayerProfileSport.builder()
                    .id(sid)
                    .playerProfile(profile)
                    .sport(sp)
                    .build()
            );
        }

        PlayerProfile updated = profileRepo.save(profile);
        return PlayerProfileMapper.toDto(updated);
    }
}