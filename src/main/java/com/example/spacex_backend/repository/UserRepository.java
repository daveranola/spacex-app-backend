package com.example.spacex_backend.repository;

import com.example.spacex_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//interface, so we can get all the methods of the JPA
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<User> findByName(String name);
}
