package com.ae.poc.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ae.poc.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
