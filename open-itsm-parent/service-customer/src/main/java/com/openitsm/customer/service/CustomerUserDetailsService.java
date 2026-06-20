package com.openitsm.customer.service;

import com.openitsm.customer.model.Customer;
import com.openitsm.customer.repository.CustomerRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final CustomerRepository repository;

    public CustomerUserDetailsService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Customer customer = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found"));

        return User.builder()
                .username(customer.getUsername())
                .password(customer.getPassword())
                .authorities("ROLE_CUSTOMER")
                .disabled(!customer.isEnabledFlag())
                .build();
    }
}