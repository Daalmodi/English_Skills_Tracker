package com.example.EnglishSkillTrackerCRUD.auth;

import com.example.EnglishSkillTrackerCRUD.dto.UserDTO;

public interface IAuthService {
    AuthResponseDTO login(LoginRequestDTO loginRequestDTO);
    UserDTO register(UserDTO userDTO);
}
