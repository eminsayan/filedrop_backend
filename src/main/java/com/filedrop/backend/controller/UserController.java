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
    public UserResource CreateUser(@RequestBody UserDto userdto){
       return userService.CreateUser(userdto);
    }

    @GetMapping("/{id}")
    public UserResource getUserById(@PathVariable UUID id){//5956125e-5861-486b-84c3-a3cd494a4d88
        return userService.getUserById(id);
    }

    @GetMapping("/username/{username}")
    public UserResource getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @GetMapping
    public List<UserResource> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public UserResource updateUser(@PathVariable UUID id, @RequestBody UserDto dto){//093ba96f-002b-44f0-8597-b6d7da6a7cb6
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public UserResource deleteUser(@PathVariable UUID id){
        return userService.deleteUser(id);
    }

}
