package com.openitsm.user.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openitsm.user.model.AppUser;
import com.openitsm.user.repository.UserRepository;

@Service
@Transactional
public class UserService {

    private static final Logger log = LogManager.getLogger(UserService.class);

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(String username, String password, String role) {

        log.info("Creating user: {}", username);

        repository.findByUsername(username)
                .ifPresent(u -> {
                    throw new RuntimeException("User already exists");
                });

        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        // FIXED ROLE FORMAT
        user.setRole("ROLE_" + role);

        user.setEnabled("Y");

        repository.save(user);
    }
}