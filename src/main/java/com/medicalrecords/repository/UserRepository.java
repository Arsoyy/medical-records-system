package com.medicalrecords.repository;

import com.medicalrecords.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository слой за работа с users таблицата.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Намира потребител по username.
     *
     * Използва се при login.
     *
     * @param username username на потребителя
     * @return Optional<User>
     */
    Optional<User> findByUsername(String username);
}