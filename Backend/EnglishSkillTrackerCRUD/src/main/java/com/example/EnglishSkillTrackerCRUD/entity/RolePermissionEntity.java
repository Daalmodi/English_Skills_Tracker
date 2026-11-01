package com.example.EnglishSkillTrackerCRUD.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(
        name = "roles_permissions",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"role_id","permission_id"})
        }
)
@Data
public class RolePermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RoleEntity role;
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "permission_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PermissionEntity permissionEntity;

}
