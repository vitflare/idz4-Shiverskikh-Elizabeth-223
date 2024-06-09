package com.idz4.usersservice.repository;

import com.idz4.usersservice.core.entities.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Sessions, Long> {
    Optional<Sessions> findByToken(String sessionToken);
}
