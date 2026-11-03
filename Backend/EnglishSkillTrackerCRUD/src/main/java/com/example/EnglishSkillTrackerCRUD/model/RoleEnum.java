package com.example.EnglishSkillTrackerCRUD.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum RoleEnum {
    ROLE_ADMIN("Administrator with full access to manage users, roles, and system settings",
    Set.of(
            PermissionEnum.USER_READ,
            PermissionEnum.USER_CREATE,
            PermissionEnum.USER_UPDATE,
            PermissionEnum.USER_DELETE,
            PermissionEnum.ROLE_ASSIGN,
            PermissionEnum.ASSIGNMENT_CREATE,
            PermissionEnum.ASSIGNMENT_READ,
            PermissionEnum.ASSIGNMENT_DELETE
    )
    ),
    ROLE_STUDENT("Learner who can access English skill tracking, view assigned lessons, and track progress", Set.of(
            PermissionEnum.SKILL_READ,
            PermissionEnum.PROGRESS_READ_OWN,
            PermissionEnum.LESSON_READ
    )
    ),
    ROLE_INSTRUCTOR("Instructor who can manage assigned learners, evaluate English skills, and track learner progress", Set.of(
            PermissionEnum.ASSIGNMENT_READ,
            PermissionEnum.SKILL_EVALUATE,
            PermissionEnum.SKILL_READ,
            PermissionEnum.SKILL_UPDATE,
            PermissionEnum.PROGRESS_READ_ALL,
            PermissionEnum.LESSON_MANAGE,
            PermissionEnum.LESSON_READ
            )
    );
    private final String description;
    private final Set<PermissionEnum> permission;
}
