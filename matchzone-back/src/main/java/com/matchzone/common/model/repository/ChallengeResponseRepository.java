package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.ChallengeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeResponseRepository extends JpaRepository<ChallengeResponse, Long> {

}
