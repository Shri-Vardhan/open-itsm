package com.openitsm.contact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactDashboardController {

    @GetMapping("/contact/dashboard")
    public String dashboard() {
        return "contact/dashboard";
    }
}