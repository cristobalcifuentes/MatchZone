package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtRepository extends JpaRepository<Court, Long> {

}
