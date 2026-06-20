package com.openitsm.application.security;

import com.openitsm.admin.service.AdminUserDetailsService;
import com.openitsm.customer.service.CustomerUserDetailsService;
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

    private final AdminUserDetailsService adminUserDetailsService;
    private final CustomerUserDetailsService customerUserDetailsService;

    public SecurityConfiguration(AdminUserDetailsService adminUserDetailsService,
                                 CustomerUserDetailsService customerUserDetailsService) {
        this.adminUserDetailsService = adminUserDetailsService;
        this.customerUserDetailsService = customerUserDetailsService;
    }

    // ================= PASSWORD ENCODER =================
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ================= ADMIN AUTH PROVIDER =================
    @Bean
    public AuthenticationProvider adminAuthProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(adminUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // ================= CUSTOMER AUTH PROVIDER =================
    @Bean
    public AuthenticationProvider customerAuthProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customerUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // ================= ADMIN SECURITY CHAIN =================
    @Bean
    @Order(1)
    public SecurityFilterChain adminSecurity(HttpSecurity http) throws Exception {

        http
                .securityMatcher("/admin/**")
                .authenticationProvider(adminAuthProvider())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/admin/login",
                                "/admin/process-login",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()

                        // ONLY ADMIN allowed
                        .anyRequest().hasRole("ADMIN")
                )
                .formLogin(form -> form
                        .loginPage("/admin/login")
                        .loginProcessingUrl("/admin/process-login")
                        .defaultSuccessUrl("/admin/dashboard", true)
                        .failureUrl("/admin/login?error=true")
                )
                .logout(logout -> logout
                        .logoutUrl("/admin/logout")
                        .logoutSuccessUrl("/admin/login?logout=true")
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    // ================= CUSTOMER SECURITY CHAIN =================
    @Bean
    @Order(2)
    public SecurityFilterChain customerSecurity(HttpSecurity http) throws Exception {

        http
                .securityMatcher("/customer/**")
                .authenticationProvider(customerAuthProvider())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/customer/login",
                                "/customer/process-login",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()

                        // ONLY CUSTOMER allowed
                        .anyRequest().hasRole("CUSTOMER")
                )
                .formLogin(form -> form
                        .loginPage("/customer/login")
                        .loginProcessingUrl("/customer/process-login")
                        .defaultSuccessUrl("/customer/dashboard", true)
                        .failureUrl("/customer/login?error=true")
                )
                .logout(logout -> logout
                        .logoutUrl("/customer/logout")
                        .logoutSuccessUrl("/customer/login?logout=true")
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}