package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.SchoolSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolSeriesRepository extends JpaRepository<SchoolSeries, Long> {

}
