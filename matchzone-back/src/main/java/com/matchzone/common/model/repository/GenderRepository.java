package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.Gender;
import com.matchzone.common.model.enums.GenderType;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {
	
	  Optional<Gender> findByCode(GenderType code);

}
