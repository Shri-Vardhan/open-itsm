package com.openitsm.security;

import com.openitsm.user.model.User;
import com.openitsm.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository.findByUsername(username.trim())
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "User not found"
                        )
                );

        return org.springframework.security.core.userdetails.User
                .withUsername(
                        user.getUsername()
                )
                .password(
                        user.getPassword()
                )
                .disabled(
                        !"Y".equalsIgnoreCase(
                                user.getEnabled()
                        )
                )
                .roles(
                        normalizeRole(
                                user.getRole()
                        )
                )
                .build();
    }

    private String normalizeRole(String role) {
        if (role == null || role.isBlank()) {
            return "USER";
        }

        String trimmedRole = role.trim();
        if (trimmedRole.startsWith("ROLE_")) {
            return trimmedRole.substring("ROLE_".length());
        }

        return trimmedRole.toUpperCase(Locale.ROOT);
    }
}
