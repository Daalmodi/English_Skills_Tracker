package com.example.EnglishSkillTrackerCRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EnglishSkillTrackerCRUD.dto.UserDTO;
import com.example.EnglishSkillTrackerCRUD.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/users")//ruta de end point  para el manejo de ususarios  CRUD 
public class UserController {
    @Autowired
    private UserService userService;

    
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDto){
        return userService.createUser(userDto);
    }

    @GetMapping
    public List<UserDTO> getUsers(){
        List<UserDTO> userDTOs = userService.getUsers();
        return userDTOs;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> editUserId(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO userDTOEdited = userService.editUserId(id,userDTO);
        
        return ResponseEntity.ok(userDTOEdited);
    }


}
