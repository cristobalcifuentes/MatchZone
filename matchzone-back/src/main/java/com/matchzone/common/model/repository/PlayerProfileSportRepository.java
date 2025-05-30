package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.PlayerProfileSport;
import com.matchzone.common.model.entities.PlayerProfileSportId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerProfileSportRepository extends JpaRepository<PlayerProfileSport, PlayerProfileSportId> {
}