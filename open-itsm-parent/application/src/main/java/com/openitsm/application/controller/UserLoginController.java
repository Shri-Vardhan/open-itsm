package com.openitsm.application.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserLoginController {

    private static final Logger log = LogManager.getLogger(UserLoginController.class);

    @GetMapping("/user/login")
    public String login() {
        log.debug("Login page requested");
        return "login/userLogin";
    }
}