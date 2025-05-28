package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.FranjaDisponible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranjaDisponibleRepository extends JpaRepository<FranjaDisponible, Long> {

}
