package com.openitsm.contact.controller;

import com.openitsm.contact.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactAuthController {

    private final ContactService contactService;

    public ContactAuthController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact/login")
    public String login() {
        return "contact/contact-login";
    }

    @PostMapping("/contact/process-login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password) {

        if (contactService.authenticate(username, password)) {
            return "redirect:/contact/dashboard";
        }

        return "redirect:/contact/login?error";
    }
}
