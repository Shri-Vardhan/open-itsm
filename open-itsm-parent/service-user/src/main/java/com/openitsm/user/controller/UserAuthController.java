package com.openitsm.user.controller;

import com.openitsm.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserAuthController {

    private final UserService userService;

    public UserAuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login")
    public String login() {
        return "user/user-login";
    }

    @PostMapping("/user/process-login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password) {

        if (userService.authenticate(username, password)) {
            return "redirect:/user/dashboard";
        }

        return "redirect:/user/login?error";
    }
}
