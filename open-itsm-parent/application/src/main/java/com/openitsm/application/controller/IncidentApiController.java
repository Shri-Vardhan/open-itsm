package com.openitsm.application.controller;

import com.openitsm.incident.domain.Incident;
import com.openitsm.incident.service.IncidentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class IncidentApiController {

    private final IncidentService service;

    public IncidentApiController(
            IncidentService service) {

        this.service = service;
    }

    @GetMapping("/incidents")
    public List<Incident> findAll() {
        return service.findAll();
    }
}