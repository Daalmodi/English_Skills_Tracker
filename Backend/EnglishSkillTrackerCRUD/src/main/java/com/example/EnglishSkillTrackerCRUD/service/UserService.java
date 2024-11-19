package com.example.EnglishSkillTrackerCRUD.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.EnglishSkillTrackerCRUD.dto.UserDTO;
import com.example.EnglishSkillTrackerCRUD.entity.UserEntity;
import com.example.EnglishSkillTrackerCRUD.repository.UserRepository;
import com.example.EnglishSkillTrackerCRUD.exceptions.ResourceNotFoundException;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDTO createUser(UserDTO userDTO) {
        UserEntity userEntity =convertEntity(userDTO);
        String passwordHash =passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(passwordHash);
        UserEntity userEntityCreated = userRepository.save(userEntity);
        return convertDTO(userEntityCreated);
    }
               
    private UserDTO convertDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setCreatedAt(userEntity.getCreatedAt());
        userDTO.setLastLoginAt(userEntity.getLastLoginAt());
        userDTO.setStatus(userEntity.getStatus());
        userDTO.setRole(userEntity.getRole());
        return userDTO;
    }
        
    private UserEntity convertEntity(UserDTO userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setCreatedAt(userDto.getCreatedAt());
        userEntity.setLastLoginAt(userDto.getLastLoginAt());
        userEntity.setStatus(userDto.getStatus());
        userEntity.setRole(userDto.getRole());
        return userEntity;
    }

    public List<UserDTO> getUsers() {
        List<UserEntity> usersEntity = userRepository.findAll();
        List<UserDTO> userDTOs = usersEntity.stream().map(this::convertDTO).collect(Collectors.toList());
        return userDTOs;
    }

    public void deleteUserById(Long id) {
       UserEntity userEntity =userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
       userRepository.delete(userEntity);
    }

        
    
}
