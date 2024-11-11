package com.example.EnglishSkillTrackerCRUD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EnglishSkillTrackerCRUD.dto.UserDTO;
import com.example.EnglishSkillTrackerCRUD.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDto){
        return userService.createUser(userDto);
    }
}
