package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.PlayerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PlayerProfileRepository extends JpaRepository<PlayerProfile, Long> {
	Optional<PlayerProfile> findByUserId(Long userId);
}
