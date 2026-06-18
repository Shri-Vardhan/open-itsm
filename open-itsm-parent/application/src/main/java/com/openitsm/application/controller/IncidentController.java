package com.openitsm.application.controller;

import com.openitsm.incident.domain.Incident;
import com.openitsm.incident.service.IncidentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IncidentController {

    private final IncidentService service;
    private static final Logger log = LogManager.getLogger(IncidentController.class);

    public IncidentController(IncidentService service) {
        this.service = service;
    }

    @GetMapping("/api/incidents")
    public List<Incident> findAll() {
        log.debug("Retrieving all incidents");
        return service.findAll();
    }
}