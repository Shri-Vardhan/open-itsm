package com.openitsm.user.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.openitsm.user.model.AppUser;
import com.openitsm.user.service.UserService;

@Controller
@RequestMapping("/users")
public class UserAdminController {

    private static final Logger logger = LogManager.getLogger(UserAdminController.class);

    private final UserService userService;

    public UserAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create-form")
    public String createForm() {
        logger.info("User create form requested");
        return "user/create-user";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute AppUser user) {
        userService.createUser(
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
        return "redirect:/user/login";
    }
}