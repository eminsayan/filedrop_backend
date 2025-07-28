package com.filedrop.backend.service;

import com.filedrop.backend.model.User;
import com.filedrop.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public User register(User user) {
        return userRepository.save(user);
    }


    public User login(User user) {
        User loggedInUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Kullanici bulunamadi: " + user.getUsername()));
        
        if (!loggedInUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("sifre hatalı");
        }

        return loggedInUser;
    }
}
