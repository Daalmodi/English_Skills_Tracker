package com.example.EnglishSkillTrackerCRUD.auth;

public record AuthResponseDTO(
    String accesToken,
    String type, // e.g.  ,"Barer "
    Long expiresIN
) { }
