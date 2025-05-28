package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.MatchParticipation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchParticipationRepository extends JpaRepository<MatchParticipation, Long> {

}
