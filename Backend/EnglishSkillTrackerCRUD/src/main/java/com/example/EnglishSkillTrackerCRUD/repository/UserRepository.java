package com.example.EnglishSkillTrackerCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EnglishSkillTrackerCRUD.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    
}
