package com.filedrop.backend.service;

import com.filedrop.backend.dto.RegisterRequest;
import com.filedrop.backend.enums.Role;
import com.filedrop.backend.model.User;
import com.filedrop.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void Register(RegisterRequest request){

        if (userRepository.findByUsername(request.getUsername()) != null){
            throw new RuntimeException("Kullanıcı zaten var!");
        }

        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);
    }
}
