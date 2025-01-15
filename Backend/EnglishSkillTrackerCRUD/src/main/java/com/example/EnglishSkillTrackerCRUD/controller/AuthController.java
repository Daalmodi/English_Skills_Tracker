package com.example.EnglishSkillTrackerCRUD.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.EnglishSkillTrackerCRUD.dto.LoginDTO;
import com.example.EnglishSkillTrackerCRUD.dto.TokenDTO;
import com.example.EnglishSkillTrackerCRUD.dto.UserDTO;
import com.example.EnglishSkillTrackerCRUD.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    
    @PostMapping("/login")//mapea la url /login con el metodo post para loguear al usuario
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {//metodo para loguear al usuario que retorna un objeto de tipo ResponseEntity con un objeto de tipo TokenDTO

        String token = authService.login(loginDTO);//obtiene el token del metodo login de la clase AuthService
        TokenDTO tokenDTO = new TokenDTO(token);//crea un objeto de tipo TokenDTO con el token
       
        return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
        
    }

    @GetMapping("/user-info")
    public ResponseEntity<UserDTO> getUserInfo(@RequestHeader("Authorization") String token) {//metodo para obtener la informacion del usuario que retorna un objeto de tipo ResponseEntity con un objeto de tipo UserDTO
        //verifica el token 
        if(!authService.verifyToken(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        //obtiene la informacion del ususario con el token 
        UserDTO userInfoDTO = authService.getUserDTO(token);
        return ResponseEntity.ok(userInfoDTO);
    }
    
    


}
