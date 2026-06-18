package com.openitsm.user.service;

import com.openitsm.user.model.AppUser;
import com.openitsm.user.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(String username, String password, String role) {

        logger.info("Creating user: {}", username);

        repository.findByUsername(username).ifPresent(u -> {
            logger.warn("User already exists: {}", username);
            throw new RuntimeException("User already exists");
        });

        AppUser user = new AppUser();
        user.setUsername(username);

        user.setPassword(passwordEncoder.encode(password));
        logger.info("**** Password : {}, Encoded Password : {}", password, passwordEncoder.encode(password));
        user.setRole(role);

        user.setEnabled("Y");

        repository.save(user);

        logger.info("User saved successfully: {}", username);
    }
}