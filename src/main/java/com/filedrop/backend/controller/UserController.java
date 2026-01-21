package com.filedrop.backend.controller;

import com.filedrop.backend.controller.dto.UserDto;
import com.filedrop.backend.controller.mapper.UserMapper;
import com.filedrop.backend.controller.resource.UserResource;
import com.filedrop.backend.model.User;
import com.filedrop.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserResource createUser(@RequestBody UserDto userdto) {
        User user = userMapper.toEntity(userdto);
        User createdUser = userService.createUser(user);
        return userMapper.toResource(createdUser);
    }

    @GetMapping("/{id}")
    public UserResource getUserById(@PathVariable UUID id) {
        User user = userService.getUserById(id);
        return userMapper.toResource(user);
    }

    @GetMapping("/username/{username}")
    public UserResource getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return userMapper.toResource(user);
    }

    @GetMapping
    public List<UserResource> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return userMapper.toResourceList(users);
    }

    @PutMapping("/{id}")
    public UserResource updateUser(@PathVariable UUID id,
                                   @RequestBody UserDto dto) {
        User user = userMapper.toEntity(dto);
        User uptadedUser = userService.updateUser(id, user);
        return userMapper.toResource(uptadedUser);
    }

    @DeleteMapping("/{id}")
    public UserResource deleteUser(@PathVariable UUID id) {
        User user = userService.deleteUser(id);
        return userMapper.toResource(user);

    }

}
