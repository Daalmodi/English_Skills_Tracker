package com.example.EnglishSkillTrackerCRUD.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleEnum {
    ROLE_ADMIN("Administrator with full access to manage users, roles, and system settings"),
    ROLE_STUDENT("Learner who can access English skill tracking, view assigned lessons, and track progress"),
    ROLE_INSTRUCTOR("Instructor who can manage assigned learners, evaluate English skills, and track learner progress");
    private final String description;
}
