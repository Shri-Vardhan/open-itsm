package com.openitsm.application.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController_UI {

    private static final Logger log = LogManager.getLogger(LoginController_UI.class);

    @GetMapping("/login")
    public String loginPage() {
        log.debug("Displaying login page");
        return "login/login";
    }
}