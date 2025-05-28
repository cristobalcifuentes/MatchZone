package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.Medal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedalRepository extends JpaRepository<Medal, Long> {

}
