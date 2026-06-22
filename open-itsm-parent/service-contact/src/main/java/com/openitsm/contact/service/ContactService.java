package com.openitsm.contact.service;

import com.openitsm.contact.model.Contact;
import com.openitsm.contact.repository.ContactRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactRepository repository;
    private final PasswordEncoder encoder;

    public ContactService(
            ContactRepository repository,
            PasswordEncoder encoder) {

        this.repository = repository;
        this.encoder = encoder;
    }

    public void createContact(
            String username,
            String password) {

        Contact contact = new Contact();

        contact.setUsername(username);
        contact.setPassword(
                encoder.encode(password)
        );

        contact.setEnabled("Y");

        repository.save(contact);
    }
}