package com.anurag.aiinfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anurag.aiinfra.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByEmail(String email);

    List<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String name,
            String email
    );
}