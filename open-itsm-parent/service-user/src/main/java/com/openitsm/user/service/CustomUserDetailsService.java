package com.openitsm.user.service;

import com.openitsm.user.model.AppUser;
import com.openitsm.user.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LogManager.getLogger(CustomUserDetailsService.class);

    private final UserRepository repository;

    public CustomUserDetailsService(UserRepository repository) {

        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("Authentication attempt for username={}", username);

        AppUser user = repository.findByUsername(username).orElseThrow(() -> {

            logger.warn("User not found: {}", username);

            return new UsernameNotFoundException(username);
        });

        logger.debug("User found. Enabled={}", user.getEnabled());

        logger.debug("Role={}", user.getRole());

        return User.builder().username(user.getUsername()).password(user.getPassword()).roles(user.getRole()).disabled(!user.isEnabledFlag()).build();
    }
}