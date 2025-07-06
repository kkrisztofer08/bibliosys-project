package com.example.library.service;

import com.example.library.controller.RegisterRequest;
import com.example.library.entity.User;
import com.example.library.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() && user.get().getPassword().equals(password);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("A felhasználónév már létezik.");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return userRepository.save(user);
    }



}
