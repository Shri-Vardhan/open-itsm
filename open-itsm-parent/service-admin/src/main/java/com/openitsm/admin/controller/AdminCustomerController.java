package com.openitsm.admin.controller;

import com.openitsm.customer.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/customers")
public class AdminCustomerController {

    private final CustomerService service;

    public AdminCustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/create-form")
    public String createForm() {
        return "customer/create-customer";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        service.createCustomer(username, password);
        return "redirect:/admin/dashboard";
    }
}