package com.openitsm.incident.controller;

import com.openitsm.incident.service.IncidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserIncidentController {

    private final IncidentService service;

    public UserIncidentController(
            IncidentService service) {

        this.service = service;
    }

    @GetMapping("/incidents")
    public String view(Model model) {

        model.addAttribute(
                "incidents",
                service.findAll()
        );

        return "incidents/incidents";
    }
}