package com.openitsm.contact.controller;

import com.openitsm.contact.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/contacts")
public class UserContactController {

    private final ContactService service;

    public UserContactController(
            ContactService service) {

        this.service = service;
    }

    @GetMapping("/create-form")
    public String createForm() {
        return "contact/create-contact";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam("username")
            String username,

            @RequestParam("password")
            String password) {

        service.createContact(
                username,
                password
        );

        return "redirect:/user/dashboard";
    }
}