package com.openitsm.contact.service;

import com.openitsm.contact.model.Contact;
import com.openitsm.contact.repository.ContactRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class ContactUserDetailsService implements UserDetailsService {

    private final ContactRepository repository;

    public ContactUserDetailsService(ContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Contact contact = repository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Contact not found: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(contact.getUsername())
                .password(contact.getPassword())
                .authorities("ROLE_CONTACT")
                .disabled(!contact.isEnabledFlag())
                .build();
    }
}