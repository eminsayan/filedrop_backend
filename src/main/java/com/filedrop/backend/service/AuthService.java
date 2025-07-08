package com.filedrop.backend.service;

import com.filedrop.backend.controller.dto.UserDto;
import com.filedrop.backend.controller.mapper.UserMapper;
import com.filedrop.backend.model.User;
import com.filedrop.backend.repository.UserRepository;
import com.filedrop.backend.controller.resource.UserResource;
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

        // Todo (MK): servis metotlarından entity dönsün. resource çevirimini controller
        // Todo (MK): katmanında yapalım (bu todo'yu da silersin)
        return userMapper.toResource(user);
    }


    public UserResource login(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new RuntimeException("Kullanici bulunamadi: " + userDto.getUsername()));

        if (!user.getPassword().equals(userDto.getPassword())) {
            throw new RuntimeException("sifre hatalı");
        }

        return userMapper.toResource(user);
    }
}
