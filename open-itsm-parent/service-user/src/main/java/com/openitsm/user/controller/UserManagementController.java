package com.openitsm.user.controller;

import com.openitsm.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/users")
public class UserManagementController {

    private final UserService service;

    public UserManagementController(UserService service) {
        this.service = service;
    }

    @GetMapping("/create-form")
    public String createForm() {
        return "user/create-user";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("role") String role) {

        service.createUser(
                username,
                password,
                role
        );

        return "redirect:/user/dashboard";
    }
}