package dev.kishore.authservice.Repository;

import dev.kishore.authservice.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    String findPasswordByEmail(String email);

   // Optional<User> findBytokenAnduserId(String token, Long userId);
}
