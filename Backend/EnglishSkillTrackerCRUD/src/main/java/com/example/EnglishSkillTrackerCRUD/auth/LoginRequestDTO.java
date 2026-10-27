package com.example.EnglishSkillTrackerCRUD.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank(message = "Username cannot be blank")
        String username,
        @NotBlank(message = "Passowrd cannot be blank")
        String password
) {}
