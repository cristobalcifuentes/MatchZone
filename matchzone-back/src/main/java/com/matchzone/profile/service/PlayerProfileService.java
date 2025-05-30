package com.matchzone.profile.service;

import com.matchzone.profile.dto.PlayerProfileDto;
import com.matchzone.profile.dto.UpdatePlayerProfileDto;

public interface PlayerProfileService {

    PlayerProfileDto getCurrentPlayerProfile();

    PlayerProfileDto updateCurrentPlayerProfile(UpdatePlayerProfileDto dto);
}