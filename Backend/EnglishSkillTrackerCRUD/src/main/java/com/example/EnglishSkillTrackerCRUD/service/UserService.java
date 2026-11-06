package com.example.EnglishSkillTrackerCRUD.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.example.EnglishSkillTrackerCRUD.service.abstraction.IUserService;
import com.example.EnglishSkillTrackerCRUD.service.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.EnglishSkillTrackerCRUD.dto.UserDTO;
import com.example.EnglishSkillTrackerCRUD.entity.RoleEntity;
import com.example.EnglishSkillTrackerCRUD.entity.UserEntity;
import com.example.EnglishSkillTrackerCRUD.entity.UserRoleEntity;
import com.example.EnglishSkillTrackerCRUD.model.RoleEnum;
import com.example.EnglishSkillTrackerCRUD.repository.RoleRepository;
import com.example.EnglishSkillTrackerCRUD.repository.UserRepository;
import com.example.EnglishSkillTrackerCRUD.repository.UserRoleRepository;
import com.example.EnglishSkillTrackerCRUD.exceptions.ResourceNotFoundException;
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity userEntity =userMapper.convertEntity(userDTO);
        String rawPassword = (userDTO.getPassword_hash() != null && !userDTO.getPassword_hash().isBlank())
                ? userDTO.getPassword_hash()
                : "TempPass123!";
        userEntity.setPassword_hash(passwordEncoder.encode(rawPassword));
        UserEntity userEntityCreated = userRepository.save(userEntity);

        RoleEnum roleToAssign = (userDTO.getUserRole() != null) ? userDTO.getUserRole() : RoleEnum.ROLE_STUDENT;
        RoleEntity roleEntity = roleRepository.findByName(roleToAssign)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUser(userEntityCreated);
        userRoleEntity.setRole(roleEntity);
        userRoleRepository.save(userRoleEntity);

        UserDTO createdDTO = userMapper.convertDTO(userEntityCreated);
        createdDTO.setUserRole(roleEntity.getName());
        return createdDTO;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<UserEntity> usersEntity = userRepository.findAll();
        return usersEntity.stream().map(user -> {
            UserDTO dto = userMapper.convertDTO(user);
            userRoleRepository.findByUserId(user.getId())
                    .ifPresent(userRole -> dto.setUserRole(userRole.getRole().getName()));
            return dto;
        }).collect(Collectors.toList());
    }
    @Override
    public void deleteUserById(UUID id) {
       UserEntity userEntity =userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
       userRepository.delete(userEntity);
    }
    @Override
    @Transactional
    public UserDTO editUserId(UUID id, UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        userEntity.setName(userDTO.getName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        if (userDTO.getStatus() != null) {
            userEntity.setStatus(userDTO.getStatus());
        }
        UserEntity userEntityEdited = userRepository.save(userEntity);

        UserDTO editedDTO = userMapper.convertDTO(userEntityEdited);
        if (userDTO.getUserRole() != null) {
            RoleEntity newRole = roleRepository.findByName(userDTO.getUserRole())
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
            UserRoleEntity userRoleEntity = userRoleRepository.findByUserId(id)
                    .orElseGet(() -> {
                        UserRoleEntity newUserRole = new UserRoleEntity();
                        newUserRole.setUser(userEntityEdited);
                        return newUserRole;
                    });
            userRoleEntity.setRole(newRole);
            userRoleEntity.setAssignedAt(LocalDateTime.now());
            userRoleRepository.save(userRoleEntity);
            editedDTO.setUserRole(newRole.getName());
        } else {
            userRoleRepository.findByUserId(id)
                    .ifPresent(userRole -> editedDTO.setUserRole(userRole.getRole().getName()));
        }
        return editedDTO;
    }

    @Override
    public UserDTO getUserById(UUID id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        UserDTO dto = userMapper.convertDTO(userEntity);
        userRoleRepository.findByUserId(id)
                .ifPresent(userRole -> dto.setUserRole(userRole.getRole().getName()));
        return dto;
    }
}
