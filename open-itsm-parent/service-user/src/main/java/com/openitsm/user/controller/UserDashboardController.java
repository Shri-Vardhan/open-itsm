package com.openitsm.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserDashboardController {

    @GetMapping("/user/dashboard")
    public String dashboard() {
        return "user/dashboard";
    }
}