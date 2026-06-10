package com.openitsm.application.controller;

import com.openitsm.incident.domain.Incident;
import com.openitsm.incident.service.IncidentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IncidentController {

    private final IncidentService service;

    public IncidentController(IncidentService service) {
        this.service = service;
    }

    @GetMapping("/api/incidents")
    public List<Incident> findAll() {
        return service.findAll();
    }
}