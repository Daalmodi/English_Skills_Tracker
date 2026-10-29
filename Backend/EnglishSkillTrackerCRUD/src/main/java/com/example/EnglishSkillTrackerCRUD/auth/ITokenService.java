package com.example.EnglishSkillTrackerCRUD.auth;

public interface ITokenService {
    String generateToken(String username);
    long getExpiration();
}
