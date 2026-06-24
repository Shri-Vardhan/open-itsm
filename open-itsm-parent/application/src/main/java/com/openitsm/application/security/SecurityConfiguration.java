package com.openitsm.application.security;

import com.openitsm.user.service.UserDetailsServiceImpl;
import com.openitsm.contact.service.ContactUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    private final UserDetailsServiceImpl userDetailsService;
    private final ContactUserDetailsService contactUserDetailsService;

    public SecurityConfiguration(
            UserDetailsServiceImpl userDetailsService,
            ContactUserDetailsService contactUserDetailsService) {

        this.userDetailsService = userDetailsService;
        this.contactUserDetailsService = contactUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean("userAuthProvider")
    public AuthenticationProvider userAuthProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean("contactAuthProvider")
    public AuthenticationProvider contactAuthProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(contactUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain userSecurity(
            HttpSecurity http,
            @org.springframework.beans.factory.annotation.Qualifier("userAuthProvider")
            AuthenticationProvider userAuthProvider) throws Exception {

        http
                .securityMatcher("/user/**")
                .authenticationProvider(userAuthProvider)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/user/login",
                                "/user/process-login",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()
                        .requestMatchers(
                                "/user/users/**",
                                "/user/contacts/**"
                        ).hasAnyRole("ADMIN", "USER")
                        .requestMatchers(
                                "/user/dashboard",
                                "/user/incidents"
                        ).hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/user/login")
                        .loginProcessingUrl("/user/process-login")
                        .defaultSuccessUrl("/user/dashboard", true)
                        .failureUrl("/user/login?error=true")
                )
                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .logoutSuccessUrl("/user/login?logout=true")
                );

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain contactSecurity(
            HttpSecurity http,
            @org.springframework.beans.factory.annotation.Qualifier("contactAuthProvider")
            AuthenticationProvider contactAuthProvider) throws Exception {

        http
                .securityMatcher("/contact/**")
                .authenticationProvider(contactAuthProvider)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/contact/login",
                                "/contact/process-login",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()
                        .anyRequest().hasRole("CONTACT")
                )
                .formLogin(form -> form
                        .loginPage("/contact/login")
                        .loginProcessingUrl("/contact/process-login")
                        .defaultSuccessUrl("/contact/dashboard", true)
                        .failureUrl("/contact/login?error=true")
                )
                .logout(logout -> logout
                        .logoutUrl("/contact/logout")
                        .logoutSuccessUrl("/contact/login?logout=true")
                );

        return http.build();
    }
}