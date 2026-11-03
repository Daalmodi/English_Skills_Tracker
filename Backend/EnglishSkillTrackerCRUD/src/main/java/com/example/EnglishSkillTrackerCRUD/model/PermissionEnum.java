package com.example.EnglishSkillTrackerCRUD.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PermissionEnum {
    // User & Role Management (Admin)
    USER_READ("View user profiles and list of users"),
    USER_CREATE("Create new user accounts"),
    USER_UPDATE("Edit existing user details and status"),
    USER_DELETE("Delete user accounts"),
    ROLE_ASSIGN("Assign or revoke roles for users"),

    // Instructor-Learner Assignments (Admin / Instructor)
    ASSIGNMENT_CREATE("Assign learners to instructors"),
    ASSIGNMENT_READ("View assigned learners and instructor assignments"),
    ASSIGNMENT_DELETE("Remove learner-instructor assignments"),

    // English Skills & Evaluation (Instructor / Student)
    SKILL_EVALUATE("Evaluate and grade learner English skills"),
    SKILL_UPDATE("Update English skill assessments and feedback"),
    SKILL_READ("View English skill evaluations and scores"),

    // Progress & Lessons (Student / Instructor)
    PROGRESS_READ_ALL("View progress reports for all assigned learners"),
    PROGRESS_READ_OWN("View own English learning progress and history"),
    LESSON_MANAGE("Create, update, or assign English lessons and activities"),
    LESSON_READ("Access and view assigned English lessons");

    private final String description;
}
