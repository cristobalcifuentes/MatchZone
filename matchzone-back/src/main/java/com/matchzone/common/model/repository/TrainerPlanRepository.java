package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.TrainerPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerPlanRepository extends JpaRepository<TrainerPlan, Long> {

}
