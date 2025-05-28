package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.CourtReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtReservationRepository extends JpaRepository<CourtReservation, Long> {

}
