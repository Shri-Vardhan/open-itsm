package com.openitsm.user.service;

import com.openitsm.user.model.User;
import com.openitsm.user.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void createUser(String username,
                           String password) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled("Y");

        try {
            repository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("User already exists");
        }
    }

    @Transactional(readOnly = true)
    public boolean authenticate(String username,
                                String password) {

        if (username == null || username.isBlank()) {
            return false;
        }

        if (password == null || password.isBlank()) {
            return false;
        }

        return repository.findByUsername(username.trim())
                .filter(user -> "Y".equalsIgnoreCase(user.getEnabled()))
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(false);
    }
}
