package com.matchzone.common.model.repository;

import com.matchzone.common.model.entities.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConnectionRepository extends JpaRepository<UserConnection, Long> {

}
