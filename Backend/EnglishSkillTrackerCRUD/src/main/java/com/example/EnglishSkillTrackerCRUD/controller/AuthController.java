package com.example.EnglishSkillTrackerCRUD.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.EnglishSkillTrackerCRUD.dto.LoginDTO;
import com.example.EnglishSkillTrackerCRUD.dto.TokenDTO;
import com.example.EnglishSkillTrackerCRUD.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
        // System.out.println("Email "+loginDTO.getEmail());
        // System.out.println("Password "+loginDTO.getPassword());
        String token = authService.login(loginDTO);
        TokenDTO tokenDTO = new TokenDTO(token);
       
        return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
        
    }
    


}
