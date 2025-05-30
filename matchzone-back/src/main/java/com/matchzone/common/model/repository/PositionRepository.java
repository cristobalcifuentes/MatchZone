package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findBySportId(Long sportId);
}