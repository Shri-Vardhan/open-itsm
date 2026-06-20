package com.openitsm.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerAuthController {

    @GetMapping("/customer/login")
    public String login() {
        return "login/customer-login";
    }
}