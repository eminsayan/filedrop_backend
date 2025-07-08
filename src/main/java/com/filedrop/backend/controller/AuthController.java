package com.filedrop.backend.controller;

import com.filedrop.backend.controller.dto.UserDto;
import com.filedrop.backend.controller.resource.UserResource;
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
    public UserResource register(@RequestBody UserDto userDto) {
        // Todo (MK): dto nesnelerinin çevrimini burada yapalım, Servis katmanı dto ve resource ile ilgilenmesin,
        //  entity ile ilgilensin. Dto ve Resource'lar dışarıdan alırken ve dışarıya dönerken kullandığımız modeller
        return authService.register(userDto);
    }

    @PostMapping("/login")
    public UserResource login(@RequestBody UserDto userDto){
        return authService.login(userDto);
    }
}
