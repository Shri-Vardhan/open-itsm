package com.openitsm.application.security;

import com.openitsm.user.service.CustomUserDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private static final Logger logger = LogManager.getLogger(SecurityConfig.class);

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {

        logger.info("Initializing Spring Security configuration.");

        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
   /*     logger.info(
                "PasswordEncoder bean creation started.");

        logger.info(
                "Expected database password format: admin123"
        );

        return NoOpPasswordEncoder.getInstance();*/

        logger.info("Authentication will validate user credentials using BCrypt password hashing.");

        logger.info("Expected database password format: BCrypt hash beginning with $2a$, $2b$, or $2y$.");

        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        logger.info("Creating DaoAuthenticationProvider for form-based authentication.");

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        logger.debug("Registering CustomUserDetailsService for user lookup operations.");

        provider.setUserDetailsService(userDetailsService);

        logger.debug("Registering PasswordEncoder for credential verification.");

        provider.setPasswordEncoder(passwordEncoder());

        logger.info("AuthenticationProvider successfully configured.");

        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        logger.info("Building Spring Security filter chain.");

        logger.info("Configuring authentication provider.");

        http.authorizeHttpRequests(auth -> auth.requestMatchers("/login").permitAll().requestMatchers("/users/create-form").permitAll().requestMatchers("/users/create").permitAll().anyRequest().authenticated());


        logger.info("Access rules configured. Login page is public. All other URLs require authentication.");

        http.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/dashboard", true).permitAll());

        logger.info("Custom login page configured at '/login'.");

        logger.info("Successful authentication will redirect users to '/dashboard'.");

        http.logout(logout -> logout.logoutSuccessUrl("/login"));

        logger.info("Logout configured. Users will be redirected to '/login' after logout.");

        logger.info("Spring Security filter chain initialization completed successfully.");

        return http.build();
    }
}