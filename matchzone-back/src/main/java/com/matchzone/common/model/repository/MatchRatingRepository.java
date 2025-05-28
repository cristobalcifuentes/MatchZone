package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.MatchRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRatingRepository extends JpaRepository<MatchRating, Long> {

}
