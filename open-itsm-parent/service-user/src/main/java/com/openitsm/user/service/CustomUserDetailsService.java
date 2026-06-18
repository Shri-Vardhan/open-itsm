package com.openitsm.user.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import com.openitsm.user.model.AppUser;
import com.openitsm.user.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger log = LogManager.getLogger(CustomUserDetailsService.class);
    private final UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Authentication attempt for username={}", username);

        AppUser user = repository.findByUsername(username).orElseThrow(() -> {
            log.warn("User not found: {}", username);
            return new UsernameNotFoundException(username);
        });

        log.debug("User found. Enabled={}", user.getEnabled());
        log.debug("Role={}", user.getRole());
        return User.builder().username(user.getUsername()).password(user.getPassword()).roles(user.getRole()).disabled(!user.isEnabledFlag()).build();
    }
}