package com.example.EnglishSkillTrackerCRUD.service.mapper;

import com.example.EnglishSkillTrackerCRUD.dto.UserDTO;
import com.example.EnglishSkillTrackerCRUD.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {
    public UserDTO convertDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setCreatedAt(userEntity.getCreatedAt());
        userDTO.setStatus(userEntity.getStatus());
//        userDTO.setRole(userEntity.getRole());
        return userDTO;
    }

    public UserEntity convertEntity(UserDTO userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        if (userDto.getCreatedAt() != null) {
            userEntity.setCreatedAt(userDto.getCreatedAt());
        }
        if (userDto.getStatus() != null) {
            userEntity.setStatus(userDto.getStatus());
        }
//        if (userDto.getRole() != null) {
//            userEntity.setRole(userDto.getRole());
//            System.out.println("Aqui va el rol");
//        }
        return userEntity;
    }
}
