package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.PlayerProfilePosition;
import com.matchzone.common.model.entities.PlayerProfilePositionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerProfilePositionRepository extends JpaRepository<PlayerProfilePosition, PlayerProfilePositionId> {
}
