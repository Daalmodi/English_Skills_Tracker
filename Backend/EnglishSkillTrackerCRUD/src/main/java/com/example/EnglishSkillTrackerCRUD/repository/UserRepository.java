package com.example.EnglishSkillTrackerCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EnglishSkillTrackerCRUD.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
    
}
