package com.openitsm.contact.service;

import com.openitsm.contact.model.Contact;
import com.openitsm.contact.repository.ContactRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactService {

    private final ContactRepository repository;
    private final PasswordEncoder encoder;

    public ContactService(ContactRepository repository,
                          PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
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
        contact.setPassword(encoder.encode(password));
        contact.setEnabled("Y");

        try {
            repository.save(contact);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Contact already exists");
        }
    }
}