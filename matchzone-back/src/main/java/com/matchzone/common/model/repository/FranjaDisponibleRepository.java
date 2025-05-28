package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.CourtTimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranjaDisponibleRepository extends JpaRepository<CourtTimeSlot, Long> {

}
