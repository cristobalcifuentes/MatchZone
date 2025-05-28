package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.CoachAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachAssignmentRepository extends JpaRepository<CoachAssignment, Long> {

}
