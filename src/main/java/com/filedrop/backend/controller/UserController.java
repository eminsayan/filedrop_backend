package com.filedrop.backend.controller;

import com.filedrop.backend.dto.UserDto;
import com.filedrop.backend.resource.UserResource;
import com.filedrop.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResource CreateUser(UserDto userdto){
       return userService.CreateUser(userdto);
    }

    @GetMapping("/{id}")
    public UserResource getUserById(UUID id){
        return userService.getUserById(id);
    }

    @GetMapping("/username/{username}")
    public UserResource getUserByUsername(String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping
    public List<UserResource> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public UserResource updateUser(UUID id, UserDto dto){
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public UserResource deleteUser(UUID id){
        return userService.deleteUser(id);
    }

}
