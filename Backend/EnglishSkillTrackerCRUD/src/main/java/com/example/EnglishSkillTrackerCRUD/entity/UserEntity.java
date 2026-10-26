package com.example.EnglishSkillTrackerCRUD.entity;
import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    @Column(nullable = false )
    @Size(min = 2 ,max = 100)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(updatable = true,nullable = true)
    private LocalDateTime createdAt = LocalDateTime.now();
    @NotNull
    @Column(unique = true,nullable = false)
    @Email(message = "Please provide a valid email address")
    private String email;
    @NotNull
    @Column( unique = true,nullable = false )
    // @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    private String password_hash;
    @Column(nullable = false, length = 10)
    private String status = "ACTIVE" ;
}
