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


    public String  login(LoginDTO loginDTO){//metodo para loguear al usuario que retorna un string que es el token

        
UserEntity userEntity = userRepository.findAll()//encuentra todos los usuarios
                                        .stream()//convierte la lista en un stream
                                        .filter(user-> {
                                        
                                        return user.getEmail().equals(loginDTO.getEmail());//filtra el usuario por email
                                    })
                                      .findFirst()//obtiene el primer usuario
                                      .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));//lanza una excepcion si no encuentra el usuario
        if(!passwordEncoder.matches(loginDTO.getPassword(), userEntity.getPassword())){//compara la contraseña ingresada con la contraseña encriptada del usuario
            throw new ResourceNotFoundException("Contraseña Incorrecta");
        }

        Map<String,Object> claims = new HashMap<>();//crea un mapa de claims de tipo string y objeto, llave valor con instancia de hashmap,hashmap es una implementacion de map que permite valores nulos y una llave nula
        claims.put("email", userEntity.getEmail());//agrega el email del usuario al mapa de claims
        claims.put("role", userEntity.getRole());//agrega el rol del usuario al mapa de claims
        return jwtEncoder.generateToken(claims);//retorna el token generado con los claims
    }

    public UserDTO getUserDTO(String token){//metodo para obtener el usuario por email que retorna un objeto de tipo UserDTO
        String email =jwtEncoder.extractEmail(token);//extrae el email del token
        UserEntity userEntity = userRepository.findAll().stream()//encuentra todos los usuarios Y los convierte en un stream
                                .filter(user-> user.getEmail()//filtra el usuario por email
                                .equals(email)).findFirst()//obtiene el primer usuario
                                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return convertDTO(userEntity);
    }

    private UserDTO convertDTO(UserEntity userEntity) {//metodo para convertir el usuario a un objeto de tipo UserDTO que retorna un objeto de tipo UserDTO
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setRole(userEntity.getRole());
        return userDTO;
    }

    public boolean verifyToken(String token) {//metodo para verificar el token que retorna un booleano
        return jwtEncoder.validateToken(token);//valida el token 
    }
}
