package com.openitsm.admin.service;

import com.openitsm.admin.model.AdminUser;
import com.openitsm.admin.repository.AdminUserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    private final AdminUserRepository repository;

    public AdminUserDetailsService(AdminUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        AdminUser user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String role = user.getRole();

        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(role)
                .disabled(!"Y".equalsIgnoreCase(user.getEnabled()))
                .build();
    }
}