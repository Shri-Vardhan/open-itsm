package com.openitsm.user.service;

import com.openitsm.user.model.User;
import com.openitsm.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {

        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createUser(String username, String password, String role) {

        User user = new User();

        user.setUsername(
                username.trim()
        );

        user.setPassword(
                passwordEncoder.encode(password)
        );

        user.setEnabled("Y");

        user.setRole(
                role.trim()
        );

        repository.save(user);
    }
}
