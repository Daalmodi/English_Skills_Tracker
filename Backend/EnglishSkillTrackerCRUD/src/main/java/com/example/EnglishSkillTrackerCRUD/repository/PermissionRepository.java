package com.example.EnglishSkillTrackerCRUD.repository;

import com.example.EnglishSkillTrackerCRUD.entity.PermissionEntity;
import com.example.EnglishSkillTrackerCRUD.model.PermissionEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository  extends JpaRepository<PermissionEntity,Long> {
    Optional<PermissionEntity> findByName(PermissionEnum permissionEnum);
}
