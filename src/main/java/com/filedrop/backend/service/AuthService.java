package com.filedrop.backend.service;

import com.filedrop.backend.dto.UserDto;
import com.filedrop.backend.mapper.UserMapper;
import com.filedrop.backend.model.User;
import com.filedrop.backend.repository.UserRepository;
import com.filedrop.backend.resource.UserResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResource register(UserDto request) {

        User user = userMapper.toEntity(request);

        userRepository.save(user);

        return userMapper.toResource(user);
    }
}
