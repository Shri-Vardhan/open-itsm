package com.openitsm.application.controller;

import com.openitsm.incident.service.IncidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IncidentViewController {

    private final IncidentService service;

    public IncidentViewController(IncidentService service) {
        this.service = service;
    }

    @GetMapping("/incidents")
    public String viewIncidents(Model model) {
        model.addAttribute("incidents", service.findAll());
        return "incidents/incidents";
    }
}