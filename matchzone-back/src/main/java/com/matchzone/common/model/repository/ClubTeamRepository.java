package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.ClubTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubTeamRepository extends JpaRepository<ClubTeam, Long> {

}
