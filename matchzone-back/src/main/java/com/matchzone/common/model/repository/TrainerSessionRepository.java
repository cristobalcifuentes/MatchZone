package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.TrainerSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerSessionRepository extends JpaRepository<TrainerSession, Long> {

}
