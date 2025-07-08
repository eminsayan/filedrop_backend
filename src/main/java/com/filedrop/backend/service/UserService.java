package com.filedrop.backend.service;

import com.filedrop.backend.controller.dto.UserDto;
import com.filedrop.backend.controller.mapper.UserMapper;
import com.filedrop.backend.model.User;
import com.filedrop.backend.repository.UserRepository;
import com.filedrop.backend.controller.resource.UserResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    // CREATE // Todo (MK): bu yorum satırlarına gerek yok, zaten metot isimleri var
    public UserResource CreateUser(UserDto userdto) {
        User user = userMapper.toEntity(userdto);
        userRepository.save(user);

        return userMapper.toResource(user);
    }

    // READ  ID'ye göre
    public UserResource getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı id: " + id));

        return userMapper.toResource(user);
    }

    // READ  Kullanıcı adına göre
    public UserResource getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı username: " + username));
        return userMapper.toResource(user);
    }

    // READ Tüm kullanıcıları listeler
    public List<UserResource> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toResource)
                .collect(Collectors.toList());
    }

    // UPDATE
    public UserResource updateUser(UUID id, UserDto dto) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setUsername(dto.getUsername());
                    existingUser.setPassword(dto.getPassword());
                    userRepository.save(existingUser);

                    UserResource resource = userMapper.toResource(existingUser);

                    return resource;
                })
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı id: " + id));
    }

    // DELETE
    public UserResource deleteUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı id: " + id));
        userRepository.deleteById(id);
        return userMapper.toResource(user);
    }

}
