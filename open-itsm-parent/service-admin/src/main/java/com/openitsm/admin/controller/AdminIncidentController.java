package com.openitsm.admin.controller;

import com.openitsm.incident.service.IncidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminIncidentController {

    private final IncidentService service;

    public AdminIncidentController(IncidentService service) {
        this.service = service;
    }

    @GetMapping("/incidents")
    public String view(Model model) {
        model.addAttribute("incidents", service.findAll());
        return "incidents/incidents";
    }
}