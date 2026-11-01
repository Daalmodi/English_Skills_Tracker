package com.example.EnglishSkillTrackerCRUD.repository;


import com.example.EnglishSkillTrackerCRUD.entity.RoleEntity;
import com.example.EnglishSkillTrackerCRUD.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<RoleEntity,Long> {

    Optional<RoleEntity> findByName(RoleEnum roleEnum);
}
