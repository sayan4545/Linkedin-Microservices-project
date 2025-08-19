package com.sayan.linkedin.UserService.repositories;

import com.sayan.linkedin.UserService.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
}
