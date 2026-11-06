package com.example.EnglishSkillTrackerCRUD.repository;

import com.example.EnglishSkillTrackerCRUD.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRoleRepository  extends JpaRepository<UserRoleEntity, Long> {
    Optional<UserRoleEntity> findByUserId(UUID userId);
}
