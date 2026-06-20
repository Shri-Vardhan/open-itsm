package com.openitsm.admin.service;

import com.openitsm.admin.model.AdminUser;
import com.openitsm.admin.repository.AdminUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {

    private final AdminUserRepository repository;
    private final PasswordEncoder encoder;

    public AdminUserService(AdminUserRepository repository,
                            PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public void createUser(String username,
                           String password,
                           String role) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("Role cannot be empty");
        }

        role = role.trim();

        if (role.startsWith("ROLE_")) {
            role = role.substring(5);
        }

        AdminUser user = new AdminUser();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRole(role);
        user.setEnabled("Y");

        repository.save(user);
    }
}