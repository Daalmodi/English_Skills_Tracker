package com.example.EnglishSkillTrackerCRUD.service.abstraction;

import com.example.EnglishSkillTrackerCRUD.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface IUserService {
     UserDTO createUser(UserDTO userDTO);
     List<UserDTO> getUsers();
     UserDTO getUserById(UUID id);
     void deleteUserById(UUID id);
     UserDTO editUserId(UUID id, UserDTO userDTO);
}
