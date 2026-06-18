package com.openitsm.application.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.openitsm.user.service.CustomUserDetailsService;


@Configuration
public class SecurityConfig {
    private static final Logger log = LogManager.getLogger(SecurityConfig.class);
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        log.info("Initializing Spring Security configuration.");
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("Validating user credentials using BCrypt password hashing.");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        log.info("Creating DaoAuthenticationProvider for form-based authentication.");

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        log.debug("Registering CustomUserDetailsService for user lookup operations.");

        provider.setUserDetailsService(userDetailsService);
        log.debug("Registering PasswordEncoder for credential verification.");

        provider.setPasswordEncoder(passwordEncoder());
        log.info("AuthenticationProvider successfully configured.");

        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("Building Spring Security filter chain.");
        log.info("Configuring authentication provider.");

        http.authorizeHttpRequests(auth -> auth.requestMatchers("/login").permitAll().requestMatchers("/users/create-form").permitAll().requestMatchers("/users/create").permitAll().anyRequest().authenticated());
        log.info("Access rules configured. Login page is public. All other URLs require authentication.");

        http.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/dashboard", true).permitAll());
        log.info("Custom login page configured at '/login'.");
        log.info("Successful authentication will redirect users to '/dashboard'.");

        http.logout(logout -> logout.logoutSuccessUrl("/login"));
        log.info("Logout configured. Users will be redirected to '/login' after logout.");
        log.info("Spring Security filter chain initialization completed successfully.");

        return http.build();
    }
}