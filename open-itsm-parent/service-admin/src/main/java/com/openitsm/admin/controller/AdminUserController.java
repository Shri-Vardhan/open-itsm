package com.openitsm.admin.controller;

import com.openitsm.admin.service.AdminUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    private final AdminUserService service;

    public AdminUserController(AdminUserService service) {
        this.service = service;
    }

    @GetMapping("/create-form")
    public String createForm() {
        return "admin/create-admin";
    }

    @PostMapping("/create")
    public String create(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("role") String role) {

        service.createUser(username, password, role);
        return "redirect:/admin/dashboard";
    }
}