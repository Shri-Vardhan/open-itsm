package com.openitsm.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserAuthController {

    @GetMapping("/user/login")
    public String login() {
        return "login/user-login";
    }
}