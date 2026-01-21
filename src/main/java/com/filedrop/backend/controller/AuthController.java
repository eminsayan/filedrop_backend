package com.filedrop.backend.controller;

import com.filedrop.backend.controller.dto.UserDto;
import com.filedrop.backend.controller.mapper.UserMapper;
import com.filedrop.backend.controller.resource.UserResource;
import com.filedrop.backend.model.User;
import com.filedrop.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public UserResource register(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User registeredUser = authService.register(user);
        return userMapper.toResource(registeredUser);
    }

    @PostMapping("/login")
    public UserResource login(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User loggenInUser = authService.login(user);
        return userMapper.toResource(loggenInUser);
    }
}
