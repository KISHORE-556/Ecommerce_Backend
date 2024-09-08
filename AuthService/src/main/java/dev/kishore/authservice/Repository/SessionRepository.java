package dev.kishore.authservice.Repository;

import dev.kishore.authservice.Model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {


    Optional<Session> findByTokenAndUser_id(String token, Long id);
}
