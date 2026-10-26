package com.example.EnglishSkillTrackerCRUD.service;

import java.util.List;
import java.util.UUID;
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
        UserEntity userEntityCreated = userRepository.save(userEntity);
        return convertDTO(userEntityCreated);
    }
               
    private UserDTO convertDTO(UserEntity userEntity) {
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
        
    private UserEntity convertEntity(UserDTO userDto) {
        UserEntity userEntity = new UserEntity();
        String rawPassword = (userDto.getPassword_hash() != null && !userDto.getPassword_hash().isBlank())
                ? userDto.getPassword_hash()
                : UUID.randomUUID().toString().substring(0, 8);
        userEntity.setPassword_hash(passwordEncoder.encode(rawPassword));
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

    public List<UserDTO> getUsers() {
        List<UserEntity> usersEntity = userRepository.findAll();
        List<UserDTO> userDTOs = usersEntity.stream().map(this::convertDTO).collect(Collectors.toList());
        return userDTOs;
    }

    public void deleteUserById(UUID id) {
       UserEntity userEntity =userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
       userRepository.delete(userEntity);
    }

    public UserDTO editUserId(UUID id, UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        userEntity.setName(userDTO.getName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
//        userEntity.setRole(userDTO.getRole());
        userEntity.setStatus(userDTO.getStatus());
        UserEntity userEntityEdited = userRepository.save(userEntity);
        return convertDTO(userEntityEdited);
    }

        
    
}
