package com.openitsm.contact.service;

import com.openitsm.contact.model.Contact;
import com.openitsm.contact.repository.ContactRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactService {

    private final ContactRepository repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void createContact(String username,
                              String password) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        Contact contact = new Contact();
        contact.setUsername(username);
        contact.setPassword(passwordEncoder.encode(password));
        contact.setEnabled("Y");

        try {
            repository.save(contact);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Contact already exists");
        }
    }

    @Transactional(readOnly = true)
    public boolean authenticate(String username,
                                String password) {

        if (username == null || username.isBlank()) {
            return false;
        }

        if (password == null || password.isBlank()) {
            return false;
        }

        return repository.findByUsername(username.trim())
                .filter(Contact::isEnabledFlag)
                .map(contact -> passwordEncoder.matches(password, contact.getPassword()))
                .orElse(false);
    }
}
