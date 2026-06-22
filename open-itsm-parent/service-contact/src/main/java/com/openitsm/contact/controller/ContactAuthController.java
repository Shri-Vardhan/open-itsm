package com.openitsm.contact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactAuthController {

    @GetMapping("/contact/login")
    public String login() {
        return "login/contact-login";
    }
}