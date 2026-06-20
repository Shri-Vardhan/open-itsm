package com.openitsm.customer.service;

import com.openitsm.customer.model.Customer;
import com.openitsm.customer.repository.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final PasswordEncoder encoder;

    public CustomerService(CustomerRepository repository,
                           PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public void createCustomer(String username,
                               String password) {

        Customer c = new Customer();
        c.setUsername(username);
        c.setPassword(encoder.encode(password));
        c.setEnabled("Y");

        repository.save(c);
    }
}