package com.openitsm.user.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.openitsm.user.model.AppUser;
import com.openitsm.user.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create-form")
    public String createForm() {
        logger.info(
                "Loading Create User page");
        return "user/create-user";
    }

    @PostMapping("/create")
    public String createUser(
            @ModelAttribute AppUser user) {
        userService.createUser(
                user.getUsername(),
                user.getPassword(),
                user.getRole());
        return "redirect:/login";
    }
}