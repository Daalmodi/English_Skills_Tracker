package com.example.EnglishSkillTrackerCRUD.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.example.EnglishSkillTrackerCRUD.service.abstraction.IUserService;
import com.example.EnglishSkillTrackerCRUD.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.EnglishSkillTrackerCRUD.dto.UserDTO;
import com.example.EnglishSkillTrackerCRUD.entity.UserEntity;
import com.example.EnglishSkillTrackerCRUD.repository.UserRepository;
import com.example.EnglishSkillTrackerCRUD.exceptions.ResourceNotFoundException;
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity userEntity =userMapper.convertEntity(userDTO);
        String rawPassword = (userDTO.getPassword_hash() != null && !userDTO.getPassword_hash().isBlank())
                ? userDTO.getPassword_hash()
                : UUID.randomUUID().toString().substring(0, 8);
        userEntity.setPassword_hash(passwordEncoder.encode(rawPassword));
        UserEntity userEntityCreated = userRepository.save(userEntity);
        return userMapper.convertDTO(userEntityCreated);
    }

    @Override
    public List<UserDTO> getUsers() {
        List<UserEntity> usersEntity = userRepository.findAll();
        List<UserDTO> userDTOs = usersEntity.stream().map(user -> userMapper.convertDTO(user)).collect(Collectors.toList());
        return userDTOs;
    }
    @Override
    public void deleteUserById(UUID id) {
       UserEntity userEntity =userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
       userRepository.delete(userEntity);
    }
    @Override
    public UserDTO editUserId(UUID id, UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        userEntity.setName(userDTO.getName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
//        userEntity.setRole(userDTO.getRole());
        userEntity.setStatus(userDTO.getStatus());
        UserEntity userEntityEdited = userRepository.save(userEntity);
        return userMapper.convertDTO(userEntityEdited);
    }
}
