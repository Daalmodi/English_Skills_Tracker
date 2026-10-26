package com.example.EnglishSkillTrackerCRUD.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class UserDTO {
    private UUID id;
    private String name;
    private String lastName;
    private String email;
    private LocalDateTime createdAt =LocalDateTime.now();
    private String status;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password_hash;


}
