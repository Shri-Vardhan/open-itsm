package com.openitsm.user.service;

import com.openitsm.user.model.User;
import com.openitsm.user.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    private final UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

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
                .roles("USER")
                .build();
    }
}