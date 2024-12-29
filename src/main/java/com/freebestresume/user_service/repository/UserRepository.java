package com.freebestresume.user_service.repository;

import com.freebestresume.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailId(String emailId);

    boolean existsByEmailId(String emailId);

}
