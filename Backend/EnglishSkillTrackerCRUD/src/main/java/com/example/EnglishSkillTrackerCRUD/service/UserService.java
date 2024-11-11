package com.example.EnglishSkillTrackerCRUD.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EnglishSkillTrackerCRUD.dto.UserDTO;
import com.example.EnglishSkillTrackerCRUD.entity.UserEntity;
import com.example.EnglishSkillTrackerCRUD.repository.UserRepository;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public UserDTO createUser(UserDTO userDTO) {
        UserEntity userEntity =convertEntity(userDTO);
        UserEntity userEntityCreated = userRepository.save(userEntity);
        return convertDTO(userEntityCreated);
    }
               
    private UserDTO convertDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        return userDTO;
    }
        
    private UserEntity convertEntity(UserDTO userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        return userEntity;
    }

        
    
}
