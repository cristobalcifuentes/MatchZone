package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.PlayerStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Long> {

}
