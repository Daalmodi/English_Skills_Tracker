package com.example.EnglishSkillTrackerCRUD.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.EnglishSkillTrackerCRUD.config.JwtEncoder;
import com.example.EnglishSkillTrackerCRUD.dto.LoginDTO;
import com.example.EnglishSkillTrackerCRUD.dto.UserDTO;
import com.example.EnglishSkillTrackerCRUD.entity.UserEntity;
import com.example.EnglishSkillTrackerCRUD.exceptions.ResourceNotFoundException;
import com.example.EnglishSkillTrackerCRUD.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired 
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public String  login(LoginDTO loginDTO){
        //System.out.println("El valor de loginDTO.getEmail es "+loginDTO.getEmail());
UserEntity userEntity = userRepository.findAll().stream()
                                      .filter(user-> {
                                        //System.out.println("el valor de user.getEmail es"+user.getEmail());
                                        return user.getEmail().equals(loginDTO.getEmail());
                                    })
                                      .findFirst()
                                      .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        if(!passwordEncoder.matches(loginDTO.getPassword(), userEntity.getPassword())){
            throw new ResourceNotFoundException("Contraseña Incorrecta");
        }

        Map<String,Object> claims = new HashMap<>();
        claims.put("email", userEntity.getEmail());
        claims.put("role", userEntity.getRole());
        return jwtEncoder.generateToken(claims);
    }

    public UserDTO getUserDTO(String token){
        String email =jwtEncoder.extractEmail(token);
        UserEntity userEntity = userRepository.findAll().stream()
                                .filter(user-> user.getEmail()
                                .equals(email)).findFirst()
                                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return convertDTO(userEntity);
    }

    private UserDTO convertDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setEmail(userEntity.getEmail());
        
        userDTO.setRole(userEntity.getRole());
        return userDTO;
    }
}
