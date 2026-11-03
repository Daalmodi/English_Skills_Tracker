package com.example.EnglishSkillTrackerCRUD.entity;

import com.example.EnglishSkillTrackerCRUD.model.PermissionEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "permissions")
@Data
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true,nullable = false)
    @Enumerated(EnumType.STRING)
    private PermissionEnum name;
    private String description;
}
