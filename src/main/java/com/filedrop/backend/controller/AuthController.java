package com.filedrop.backend.controller;

import com.filedrop.backend.dto.UserDto;
import com.filedrop.backend.resource.UserResource;
import com.filedrop.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public UserResource register(@RequestBody UserDto request) {

        return authService.register(request);
    }
}