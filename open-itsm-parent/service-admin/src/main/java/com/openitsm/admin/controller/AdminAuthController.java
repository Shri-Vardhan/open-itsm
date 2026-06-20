package com.openitsm.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminAuthController {

    @GetMapping("/admin/login")
    public String login() {
        return "login/admin-login";
    }
}