package com.openitsm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class UserSecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public UserSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/user/login",
                                "/css/**",
                                "/js/**"
                        )
                        .permitAll()

                        .anyRequest()
                        .authenticated()
                )
                .userDetailsService(userDetailsService)
                .formLogin(form -> form
                        .loginPage("/user/login")
                        .loginProcessingUrl("/user/process-login")
                        .defaultSuccessUrl(
                                "/user/dashboard",
                                true
                        )
                        .failureUrl(
                                "/user/login?error"
                        )
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .logoutSuccessUrl(
                                "/user/login"
                        )
                );
        return http.build();
    }
}
