package com.openitsm.user.service;

import com.openitsm.user.model.User;
import com.openitsm.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository,
                       PasswordEncoder encoder) {

        this.repository = repository;
        this.encoder = encoder;
    }

    public void createUser(
            String username,
            String password,
            String role) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException(
                    "Username cannot be empty");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException(
                    "Password cannot be empty");
        }

        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException(
                    "Role cannot be empty");
        }

        role = role.trim();

        if (role.startsWith("ROLE_")) {
            role = role.substring(5);
        }

        User user = new User();

        user.setUsername(username);
        user.setPassword(
                encoder.encode(password)
        );

        user.setRole(role);
        user.setEnabled("Y");

        repository.save(user);
    }
}