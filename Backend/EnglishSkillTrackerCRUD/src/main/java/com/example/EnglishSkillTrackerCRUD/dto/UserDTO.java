package com.example.EnglishSkillTrackerCRUD.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class UserDTO {
    private UUID id;
    @NotBlank(message = "Name is required")
    @Size(min = 2,max = 100)
    private String name;
    @NotBlank(message = "LastName is required")
    @Size(min = 2, max = 100)
    private String lastName;
    @NotBlank
    @Email(message = "Please provide a valid email address")
    private String email;
    private LocalDateTime createdAt =LocalDateTime.now();
    private String status;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password Must be at least  characters and include uppercase, a number and a special character"
    )
    private String password_hash;


}
