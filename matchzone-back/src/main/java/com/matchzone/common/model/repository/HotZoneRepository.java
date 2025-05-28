package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.HotZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotZoneRepository extends JpaRepository<HotZone, Long> {

}
