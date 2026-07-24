package com.openitsm.user.service;


import com.openitsm.user.model.User;
import com.openitsm.user.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class UserService {



    private final UserRepository repository;


    private final BCryptPasswordEncoder passwordEncoder;



    public UserService(
            UserRepository repository,
            BCryptPasswordEncoder passwordEncoder) {

        this.repository = repository;

        this.passwordEncoder = passwordEncoder;
    }




    @Transactional
    public void createUser(
            String username,
            String password) {



        User user = new User();


        user.setUsername(
                username.trim()
        );


        user.setPassword(
                passwordEncoder.encode(password)
        );


        user.setEnabled("Y");



        repository.save(user);
    }
}